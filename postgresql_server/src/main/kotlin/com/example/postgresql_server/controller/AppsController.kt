package com.example.postgresql_server.controller

import com.example.postgresql_server.model.*
import com.example.postgresql_server.repository.Equipment
import com.example.postgresql_server.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.Instant


@RestController
//@CrossOrigin("http://localhost:3030")建置後
@CrossOrigin("http://localhost:8081")
@RequestMapping("/api")
class AppsController(private val userRepository: UserRepository,val equipment: Equipment) {

    //取回所有會員
    @GetMapping
    fun allUsers(): MutableList<User> =
        userRepository.findAll()

    //會員登入
    @PostMapping("/login")
    fun login(@RequestBody userLogin: User): UserIdDto {
        val resultId = userRepository.findByUserId(userLogin.userId)
        val result = userRepository.findAllByUserIdAndPassword(userLogin.userId, userLogin.password)
        if(resultId.isEmpty()){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "帳號輸入錯誤，為方便測試直接建立帳號密碼")
        }else if(result.isEmpty()){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "密碼輸入錯誤")
        }else{
            var res=result[0]//取回第一筆資料
            return UserIdDto(res.userId)
        }
    }
    //取會員資料
    @PostMapping("/personal")
    fun personal(@RequestBody personal: UserIdDto): UserDto {
        val resultId = userRepository.findByUserId(personal.userId)
        if(resultId.isEmpty()){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "會員錯誤")
        }else{
            var res=resultId[0]//取回第一筆資料
            return sendToUserDto(res)
        }
    }

    //會員註冊
    @PostMapping("/register")
    fun register(@RequestBody register: User): ResponseEntity<UserDto> {
        val result=userRepository.findByUserId(register.userId)
        result.ifEmpty {
            val res=userRepository.save(register)
            return ResponseEntity.ok().body(sendToUserDto(res))
        }
        throw ResponseStatusException(
            HttpStatus.BAD_REQUEST, "已有此會員"
        )
    }

    //更新會員資料
    @PutMapping("/saver")
    fun saver(@RequestBody userSave: UserSave): UserDto {
        val result = userSave.userId.let {
            userRepository.findByUserId(it).map { currentUser ->
                val updatedUser: User =
                    currentUser
                        .copy(
                            updatedAt= Instant.now(),
                            userName = userSave.userName,
                            role = userSave.role,
                        )
                userRepository.save(updatedUser)
            }
        }
        result.ifEmpty {throw ResponseStatusException(HttpStatus.BAD_REQUEST, "會員錯誤")}
        var res=result[0]//取回第一筆資料
        return sendToUserDto(res)
    }

    //更新裝備狀態
    @PutMapping("/useEquipment")
    fun useEquipment(@RequestBody userSave: UserSave) {
       return equipment.findByequipmentId(userSave.id,userSave.equipmentId,userSave.userUse)
    }



}

