package com.example.postgresql_server.service

import com.example.postgresql_server.dataModel.User
import com.example.postgresql_server.dto.UserDto
import com.example.postgresql_server.dto.UserIdDto
import com.example.postgresql_server.dto.userConvertDto
import com.example.postgresql_server.input.UserInput
import com.example.postgresql_server.repository.UserByEm
import com.example.postgresql_server.repository.UserRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userByEm: UserByEm,
) {
    fun getUsers(): MutableList<User> =
        userRepository.findAll()

    fun getByUserIdAndPassword(userLogin: User): UserIdDto {
        try {
            val result = userRepository.findAllByUserIdAndPassword(userLogin.userId, userLogin.password)
            return UserIdDto(result.userId)
        } catch (exception: EmptyResultDataAccessException) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST, exception.message)
        }
    }

    fun addUser(register: User): UserIdDto {
        try {
            userRepository.findByUserId(register.userId)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        } catch (exception: EmptyResultDataAccessException) {
            val result = userRepository.save(register)
            return UserIdDto(result.userId)
        }
    }

    fun getUserData(personal: UserIdDto): User {
        try {
            return userRepository.findByUserId(personal.userId)
        } catch (exception: EmptyResultDataAccessException) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST, exception.message)
        }
    }

    fun updateUserData(userInput: UserInput): UserDto {
        try {
            val res = userByEm.updateByUserId(userInput.id, userInput.userName, userInput.role)
            return userConvertDto(res)
        } catch (exception: NullPointerException) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST, exception.message)
        }
    }

}