package com.example.postgresql_server.controller


import com.example.postgresql_server.input.*
import com.example.postgresql_server.repository.EquipmentRepository
import com.example.postgresql_server.repository.MulEquipmentRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@RestController
@CrossOrigin("http://localhost:8081")
@RequestMapping("/equipment")
class EquipmentController(private val mulEquipmentRepository: MulEquipmentRepository, private val equipmentRepository: EquipmentRepository) {


    //裝上裝備槽
    @PutMapping("/useEquipment")
    fun useEquipment(@RequestBody userInput: UserInput): ResponseEntity<Int> {
        try {
            val result= mulEquipmentRepository.useEquipment(userInput.id,userInput.weaponSlot,userInput.armorSlot)
            if(result!=0){
                return ResponseEntity.ok().body(result)
            }else{
                throw ResponseStatusException(HttpStatus.NOT_FOUND)
            }
        }catch (exception: DataIntegrityViolationException){
            throw exception
        }

    }

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

