package com.example.postgresql_server.controller

import com.example.postgresql_server.model.User
import com.example.postgresql_server.model.UserDto
import com.example.postgresql_server.model.UserSave
import com.example.postgresql_server.repository.UserRepository
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.Instant


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
    @PostMapping("/login")
    fun login(@RequestBody userLogin: User): List<User> {
        val resultId = userRepository.findByUserId(userLogin.userId)
        val result = userRepository.findAllByUserIdAndPassword(userLogin.userId, userLogin.password)
        if(resultId.isEmpty()){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "帳號輸入錯誤，為方便測試直接建立帳號密碼")
        }else if(result.isEmpty()){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "密碼輸入錯誤")
        }else{
            return result
        }
    }

    //會員註冊
    @PostMapping("/register")
    fun register(@RequestBody register: User): ResponseEntity<User> {
        val result = userRepository.findByUserId(register.userId)
        if (result.isEmpty()) return ResponseEntity.ok().body(userRepository.save(register))
        else throw ResponseStatusException(
            HttpStatus.BAD_REQUEST, "已有此會員"
        )
    }

    //更新會員資料
    @PutMapping("/saver")
    fun saver(@RequestBody userSave: UserSave){
        val res=userRepository.findByUserId(userSave.userId).map { currentUser ->
            val updatedUser: User =
                currentUser
                    .copy(
                        updatedAt= Instant.now(),
                        userName = userSave.userName,
                        role = userSave.role,
                    )
           userRepository.save(updatedUser)
        }

        val modelMapper = ModelMapper()
        val userDTO: UserDto = modelMapper.map(res, UserDto::class.java)
        println(userDTO)
//        result.ifEmpty {throw ResponseStatusException(HttpStatus.BAD_REQUEST, "會員錯誤")}

    }

}

