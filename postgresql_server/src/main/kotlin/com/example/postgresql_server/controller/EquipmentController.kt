package com.example.postgresql_server.controller

import com.example.postgresql_server.model.*
import com.example.postgresql_server.repository.AddEquipmentRepository
import com.example.postgresql_server.repository.EquipmentRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@RestController
//@CrossOrigin("http://localhost:3030")建置後
@CrossOrigin("http://localhost:8081")
@RequestMapping("/equipment")
class EquipmentController(private val equipmentRepository:EquipmentRepository,private val addEquipmentRepository: AddEquipmentRepository) {

    //更新裝備狀態
    @PutMapping("/useEquipment")
    fun useEquipment(@RequestBody userSave: UserSave) {
        return equipmentRepository.UpdateEquipment(userSave.id,userSave.equipmentId,userSave.userUse)
    }

    //鍛造裝備
    @PostMapping("/forgingEquipment")
    fun forgingEquipment(@RequestBody userEquipment: UserEquipment): UserEquipment {
        val computeValue: MutableList<EquipmentType>? =
            equipmentRepository.ForgingEquipment(userEquipment.equipmentType)
        val attribute = (1..50).random()//屬性值跑隨機

        if (computeValue?.get(0)?.equipmentType == "weapon") {
            userEquipment.equipmentAtk = attribute * computeValue[0].equipmentBasicAtk!!//攻擊屬性
            userEquipment.equipmentName = computeValue[0].equipmentType//初始裝備名稱
            return addEquipmentRepository.save(userEquipment)
        } else if (computeValue?.get(0)?.equipmentType == "armor") {
            userEquipment.equipmentDef = attribute * computeValue[0].equipmentBasicDef!!//防禦屬性
            userEquipment.equipmentName = computeValue[0].equipmentType//初始裝備名稱
            return addEquipmentRepository.save(userEquipment)
        }else{
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "鍛造錯誤")
        }
    }

}

