package com.example.postgresql_server.controller

import com.example.postgresql_server.dto.UserDto
import com.example.postgresql_server.dto.sendSlotToUserDto
import com.example.postgresql_server.input.*

import com.example.postgresql_server.repository.EquipmentRepository
import com.example.postgresql_server.repository.MulEquipmentRepository
import com.example.postgresql_server.repository.UserRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@RestController
@CrossOrigin("http://localhost:8081")
@RequestMapping("/equipment")
class EquipmentController(private val mulEquipmentRepository: MulEquipmentRepository, private val equipmentRepository: EquipmentRepository,private val userRepository: UserRepository) {


    //裝上裝備槽
//    @PutMapping("/useEquipment")
//    fun useEquipment(@RequestBody userInput: UserInput): UserDto {
//        try {
//            mulEquipmentRepository.useEquipment(userInput.userId,userInput.weaponSlot,userInput.armorSlot)
//            val result = userRepository.findByUserId(userInput.userId)
//            println(result)
//            val weaponSlot = mulEquipmentRepository.equipmentSlot(result.weaponSlot)
//            println(weaponSlot)
//            val armorSlot = mulEquipmentRepository.equipmentSlot(result.armorSlot)
//            println(armorSlot)
//            return sendSlotToUserDto(result,weaponSlot,armorSlot)
//        }catch (exception: EmptyResultDataAccessException){
//            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "會員錯誤")
//        }
//
//    }

    //裝備名稱修改
    @PutMapping("/equipmentName")
    fun equipmentName(@RequestBody inputEquipmentName:EquipmentInput){
        inputEquipmentName.equipmentName?.let {
            mulEquipmentRepository.equipmentName(inputEquipmentName.userId,inputEquipmentName.equipmentId,
                it)
        }
    }

    //刪除裝備
    @PostMapping("/delEquipment")
    fun delEquipment(@RequestBody inputEquipmentId:EquipmentInput): ResponseEntity<Int> {
        val result=mulEquipmentRepository.delEquipment(inputEquipmentId.userId,inputEquipmentId.equipmentId)
        return ResponseEntity.ok().body(result)
    }

    //鍛造裝備
    @PostMapping("/forgingEquipment")
    fun forgingEquipment(@RequestBody userEquipment: UserEquipment): UserEquipment {
        val computeValue: MutableList<EquipmentType>? =
            mulEquipmentRepository.forgingEquipment(userEquipment.equipmentType)
        val attribute = (1..50).random()//屬性值跑隨機

        return when (computeValue?.get(0)?.equipmentType) {
            "weapon" -> {
                userEquipment.equipmentAtk = attribute * computeValue[0].equipmentBasicAtk!!//攻擊屬性
                userEquipment.equipmentName = userEquipment.equipmentName//裝備名稱
                equipmentRepository.save(userEquipment)
            }
            "armor" -> {
                userEquipment.equipmentDef = attribute * computeValue[0].equipmentBasicDef!!//防禦屬性
                userEquipment.equipmentName = userEquipment.equipmentName//裝備名稱
                equipmentRepository.save(userEquipment)
            }
            else -> {
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, "鍛造錯誤")
            }
        }
    }

}

