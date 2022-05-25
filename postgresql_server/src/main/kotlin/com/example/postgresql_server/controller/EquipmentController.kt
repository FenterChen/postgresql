package com.example.postgresql_server.controller

import com.example.postgresql_server.model.*

import com.example.postgresql_server.repository.EquipmentRepository
import com.example.postgresql_server.repository.MulEquipmentRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@RestController
//@CrossOrigin("http://localhost:3030")建置後
@CrossOrigin("http://localhost:8081")
@RequestMapping("/equipment")
class EquipmentController(private val mulEquipmentRepository: MulEquipmentRepository, private val equipmentRepository: EquipmentRepository) {

    //裝備名稱修改
    @PutMapping("/equipmentName")
    fun equipmentName(@RequestBody inputEquipmentName:EquipmentInput){
        mulEquipmentRepository.equipmentName(inputEquipmentName.userId,inputEquipmentName.equipmentId,inputEquipmentName.equipmentName)
    }

    //刪除裝備
    @DeleteMapping("/delEquipment")
    fun delEquipment(@RequestBody inputEquipmentId:EquipmentInput){
        mulEquipmentRepository.delEquipment(inputEquipmentId.userId,inputEquipmentId.equipmentId)
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
                userEquipment.equipmentName = computeValue[0].equipmentType//初始裝備名稱
                equipmentRepository.save(userEquipment)
            }
            "armor" -> {
                userEquipment.equipmentDef = attribute * computeValue[0].equipmentBasicDef!!//防禦屬性
                userEquipment.equipmentName = computeValue[0].equipmentType//初始裝備名稱
                equipmentRepository.save(userEquipment)
            }
            else -> {
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, "鍛造錯誤")
            }
        }
    }

}

