package com.example.postgresql_server.controller

import com.example.postgresql_server.model.*
import com.example.postgresql_server.repository.MulEquipmentRepository

import com.example.postgresql_server.repository.UpdateUser
import com.example.postgresql_server.repository.UserRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@RestController
//@CrossOrigin("http://localhost:3030")建置後
@CrossOrigin("http://localhost:8081")
@RequestMapping("/api")
class UserController(private val userRepository: UserRepository,private val updateUser: UpdateUser,private val mulEquipmentRepository: MulEquipmentRepository) {

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
        }catch (exception: EmptyResultDataAccessException){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "帳號密碼輸入錯誤，為方便測試直接建立帳號密碼")
        }
    }

    //會員註冊
    @PostMapping("/register")
    fun register(@RequestBody register: User): UserDto {
        try {
            userRepository.findByUserId(register.userId)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "已有此會員，請重新輸入")
        }catch (exception: EmptyResultDataAccessException){
        val res=userRepository.save(register)
        return sendToUserDto(res)
        }

    }

    //取會員資料
    @PostMapping("/personal")
    fun personal(@RequestBody personal: UserIdDto): UserDto {
        try {
            val result = userRepository.findByUserId(personal.userId)
            val weaponSlot = mulEquipmentRepository.equipmentSlot(result.weaponSlot)
            val armorSlot = mulEquipmentRepository.equipmentSlot(result.armorSlot)
            return sendSlotToUserDto(result,weaponSlot,armorSlot)
        }catch (exception: EmptyResultDataAccessException){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "會員錯誤")
        }
    }

    //更新會員資料
    @PutMapping("/saver")
    fun saver(@RequestBody userInput: UserInput): UserDto {
        try {
            updateUser.updateByUserId(userInput.userId,userInput.userName,userInput.role,userInput.weaponSlot,userInput.armorSlot)
            val result = userRepository.findByUserId(userInput.userId)
            val weaponSlot = mulEquipmentRepository.equipmentSlot(result.weaponSlot)
            val armorSlot = mulEquipmentRepository.equipmentSlot(result.armorSlot)
            return sendSlotToUserDto(result,weaponSlot,armorSlot)
        }catch (exception: EmptyResultDataAccessException){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "會員錯誤")
        }

    }

}

