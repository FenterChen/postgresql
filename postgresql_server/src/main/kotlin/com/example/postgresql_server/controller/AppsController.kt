package com.example.postgresql_server.controller

import com.example.postgresql_server.model.User
import com.example.postgresql_server.model.UserInfo
import com.example.postgresql_server.model.UserLogin
import com.example.postgresql_server.model.UserSave
import com.example.postgresql_server.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@RestController
//@CrossOrigin("http://localhost:3030")建置後
@CrossOrigin("http://localhost:8081")
@RequestMapping("/api")
class AppsController(private val userRepository: UserRepository) {
    //取回所有會員
    @GetMapping
    fun getallusers(): MutableList<User> =
        userRepository.findAll()

    //會員登入
    @PostMapping("/user")
    fun selectuser(@RequestBody userLogin: UserLogin): List<UserInfo> {
        val result = userRepository.findAllByUserIdAndPassword(userLogin.userId, userLogin.password)
        return result.ifEmpty {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST, "帳密輸入錯誤"
            )
        }
    }

    //更新會員資料
    @PutMapping("/saver")
    fun saveuser(@RequestBody userSave: UserSave): List<ResponseEntity<User>> {
        val result = userRepository.findByUserId(userSave.userId).map { currentUser ->
            val updatedUser: User =
                currentUser
                    .copy(
                        userName = userSave.userName,
                        role = userSave.role,
                    )
            ResponseEntity.ok().body(userRepository.save(updatedUser))
        }
        return result.ifEmpty {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST, "會員錯誤"
            )
        }
    }
}