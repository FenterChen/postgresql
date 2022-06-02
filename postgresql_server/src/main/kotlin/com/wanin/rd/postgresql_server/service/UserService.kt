package com.wanin.rd.postgresql_server.service

import com.wanin.rd.postgresql_server.dataModel.User
import com.wanin.rd.postgresql_server.dto.UserDto
import com.wanin.rd.postgresql_server.dto.UserIdDto
import com.wanin.rd.postgresql_server.input.UserInput
import com.wanin.rd.postgresql_server.repository.UserByEm
import com.wanin.rd.postgresql_server.repository.UserRepository
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

    fun getByUserIdAndPassword( userId: String, password:String): UserIdDto {
        try {
            val result = userRepository.findAllByUserIdAndPassword(userId,password)
            return UserIdDto(result.userId)//僅傳回userId
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
            return UserIdDto(result.userId)//僅傳回userId
        }
    }

    fun findUserByUserId(userId: String): UserDto {
        try {
            val result= userRepository.findByUserId(userId)
            return result.convertToUserVo()
        } catch (exception: EmptyResultDataAccessException) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST, exception.message)
        }
    }

    fun updateUserData(id:Int,userName:String?,role:String?): UserDto {
        try {
            val result = userByEm.updateByUserId(id,userName,role)
            return result.convertToUserVo()
        } catch (exception: NullPointerException) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST, exception.message)
        }
    }

}