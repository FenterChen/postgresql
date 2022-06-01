package com.example.postgresql_server.controller

import com.example.postgresql_server.dataModel.User
import com.example.postgresql_server.dto.UserDto
import com.example.postgresql_server.dto.UserIdDto
import com.example.postgresql_server.dto.userConvertDto
import com.example.postgresql_server.input.*
import com.example.postgresql_server.repository.UserByEm
import com.example.postgresql_server.repository.UserRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping("/api")
class UserController(
    private val userRepository: UserRepository,
    private val userByEm: UserByEm,
) {
    //取回所有會員
    @GetMapping
    fun allUsers(): MutableList<User> =
        userRepository.findAll()

    //會員登入
    @PostMapping("/login")
    fun login(@RequestBody userLogin: User): UserIdDto {
        try {
            val result = userRepository.findAllByUserIdAndPassword(userLogin.userId, userLogin.password)
            return UserIdDto(result.userId)
        } catch (exception: EmptyResultDataAccessException) {
            throw exception
        }
    }

    //會員註冊
    @PostMapping("/register")
    fun register(@RequestBody register: User): UserIdDto {
        try {
            userRepository.findByUserId(register.userId)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        } catch (exception: EmptyResultDataAccessException) {
            val result = userRepository.save(register)
            return UserIdDto(result.userId)
        }
    }

    //取會員資料
    @PostMapping("/personal")
    fun personal(@RequestBody personal: UserIdDto): User {
        try {
            return userRepository.findByUserId(personal.userId)
        } catch (exception: EmptyResultDataAccessException) {
            throw exception
        }
    }

    //更新會員資料
    @PutMapping("/saver")
    fun saver(@RequestBody userInput: UserInput): UserDto {
        try {
            val res = userByEm.updateByUserId(userInput.id, userInput.userName, userInput.role)
            return userConvertDto(res)
        } catch (exception: NullPointerException) {
            throw exception
        }

    }

}

