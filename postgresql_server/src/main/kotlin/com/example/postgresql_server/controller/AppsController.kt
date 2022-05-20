package com.example.postgresql_server.controller

import com.example.postgresql_server.model.User
import com.example.postgresql_server.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
//@CrossOrigin("http://localhost:3030")建置後
@CrossOrigin("http://localhost:8081")
class `AppsController.kt`(private val userRepository: UserRepository) {
    @GetMapping("/")
    fun getallusers(): MutableList<User> =
        userRepository.findAll()

    @PostMapping("/user")
    fun selectuser(userId: String, password: String): Any {
        val result = userRepository.findAllByUserIdAndPassword(userId, password)
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("帳密錯誤");
        } else {
            return result
        }
    }

    @PostMapping("/saveuser")
    fun saveuser(userId: String, userName: String?, role: String?): ResponseEntity<Nothing> {
//        val result=userRepository.findByUserId(userId).map { userName->userName,role->role}

//        println(result)

//        userRepository.saveAndFlush(user)

        return ResponseEntity.ok().body(null)

//        return if (result.isEmpty()) {
//            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("修改失敗");
//        }else{
//            result
//        }
        }



}
