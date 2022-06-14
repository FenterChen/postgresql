package com.wanin.rd.postgresql_server.controller

import com.wanin.rd.postgresql_server.dataModel.User
import com.wanin.rd.postgresql_server.dto.UserDto
import com.wanin.rd.postgresql_server.dto.UserIdDto
import com.wanin.rd.postgresql_server.input.UserInfoInput
import com.wanin.rd.postgresql_server.input.UserLoginInput
import com.wanin.rd.postgresql_server.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import springfox.documentation.annotations.ApiIgnore


//https://localhost:8080/swagger-ui/index.html
@Api(tags = ["UserController"], description = "會員")
@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @ApiOperation("取回所有會員")
    @GetMapping
    fun getUsers(): MutableList<User> =
        userService.getUsers()

    @ApiOperation("會員登入")
    @PostMapping("/login")//userId:String,password:String
    fun login(@ApiParam(required = true, value = "會員登入所需參數") @RequestBody userLoginInput: UserLoginInput): UserIdDto {
        try {
            return userService.getByUserIdAndPassword(userLoginInput.userId, userLoginInput.password)
        } catch (exception: Exception) {
            throw ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, exception.message)
        }
    }

    @ApiOperation("會員註冊(為方便測試，會員登入時，無此帳號會直接註冊)")
    @ApiIgnore
    @PostMapping("/register")//userId: String,password: String
    fun register(@RequestBody register: User): UserIdDto {
        return userService.addUser(register)
    }

    @ApiOperation("取會員資料")
    @ApiImplicitParam(name = "userId",
        value = "會員帳號",
        required = true,
        paramType = "path",
        example = "test"
    )
    @GetMapping("/{userId}")//userId:String
    fun findUserByUserId(@PathVariable userId: String): UserDto {
        try {
            return userService.findUserByUserId(userId)
        } catch (exception: Exception) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST, exception.message)
        }
    }

    @ApiOperation("更新會員資料")
    @PutMapping()
    fun updateUserData(@ApiParam(value = "更新會員資料所需參數") @RequestBody userInfoInput: UserInfoInput) =
        try {
            userService.updateUserData(userInfoInput.id, userInfoInput.userName, userInfoInput.role)
        } catch (exception: Exception) {
            throw ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, exception.message)
        }

}

