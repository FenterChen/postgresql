package com.example.postgresql_server.controller

import com.example.postgresql_server.dataModel.User
import com.example.postgresql_server.dto.UserDto
import com.example.postgresql_server.dto.UserIdDto
import com.example.postgresql_server.input.*
import com.example.postgresql_server.service.UserService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class UserController(private val userService: UserService) {
    //取回所有會員
    @GetMapping
    fun allUsers(): MutableList<User> =
        userService.getUsers()

    //會員登入
    @PostMapping("/login")
    fun login(@RequestBody userLogin: User): UserIdDto {
        return userService.getByUserIdAndPassword(userLogin)
    }

    //會員註冊
    @PostMapping("/register")
    fun register(@RequestBody register: User): UserIdDto {
        return userService.addUser(register)
    }

    //取會員資料
    @PostMapping("/personal")
    fun personal(@RequestBody personal: UserIdDto): User {
        return userService.getUserData(personal)
    }

    //更新會員資料
    @PutMapping("/saver")
    fun saver(@RequestBody userInput: UserInput): UserDto {
        return userService.updateUserData(userInput)
    }

}

