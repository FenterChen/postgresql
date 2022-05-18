package com.example.postgresql_server.controller

import com.example.postgresql_server.model.User
import com.example.postgresql_server.model.userNops
import com.example.postgresql_server.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*



@RestController
@CrossOrigin("http://localhost:3030")
class AppsController(private val userRepository: UserRepository) {
    @GetMapping("/")
    fun getallusers(): MutableList<User> =
        userRepository.findAll()
    @PostMapping("/user")
    fun selectuser(userId: String,password: String): Any {
        val result=userRepository.findAllByUserIdAndPassword(userId, password)
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("帳密錯誤");
        }else{
            return result
        }
    }

}


