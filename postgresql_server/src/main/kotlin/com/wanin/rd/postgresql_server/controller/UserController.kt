package com.wanin.rd.postgresql_server.controller

import com.wanin.rd.postgresql_server.dataModel.User
import com.wanin.rd.postgresql_server.dto.UserDto
import com.wanin.rd.postgresql_server.dto.UserIdDto
import com.wanin.rd.postgresql_server.input.UserInput
import com.wanin.rd.postgresql_server.input.UserLoginInput
import com.wanin.rd.postgresql_server.service.UserService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {
    //取回所有會員
    @GetMapping
    fun getUsers(): MutableList<User> =
        userService.getUsers()

    //會員登入
    //userId:String,password::String
    @PostMapping("/login")
    fun login(@RequestBody userLoginInput: UserLoginInput): UserIdDto {
        return userService.getByUserIdAndPassword(userLoginInput.userId, userLoginInput.password)
    }

    //會員註冊
    //userId: String,password: String
    @PostMapping("/register")
    fun register(@RequestBody register: User): UserIdDto {
        return userService.addUser(register)
    }

    //取會員資料
    //userId: String
    @GetMapping("/{userId}")
    fun findUserByUserId(@PathVariable userId: String): UserDto {
        return userService.findUserByUserId(userId)
    }

    //更新會員資料
    //id: Int,userName: String?,role: String?
    @PutMapping()
    fun updateUserData(@RequestBody userInput: UserInput) {
        userService.updateUserData(userInput.id, userInput.userName, userInput.role)
    }

}

