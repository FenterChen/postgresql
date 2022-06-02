package com.example.postgresql_server.controller


import com.example.postgresql_server.dataModel.UserEquipment
import com.example.postgresql_server.input.*
import com.example.postgresql_server.service.EquipmentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/equipment")
class EquipmentController(
    private val equipmentService: EquipmentService,
) {


    //裝上裝備槽
    @PutMapping("/useEquipment")
    fun useEquipment(@RequestBody userInput: UserInput): ResponseEntity<Int> {
        return equipmentService.updateUserEquipment(userInput)
    }

    //裝備名稱修改
    @PutMapping("/equipmentName")
    fun equipmentName(@RequestBody inputEquipmentName: EquipmentInput): ResponseEntity<Int> {
        return equipmentService.updateWeaponOrArmorName(inputEquipmentName)
    }

    //刪除裝備
    @PostMapping("/delEquipment")
    fun delEquipment(@RequestBody inputEquipmentId: EquipmentInput): ResponseEntity<Int> {
        return equipmentService.delByUserIdAndEquipmentId(inputEquipmentId)
    }

    //鍛造裝備
    @PostMapping("/forgingEquipment")
    fun forgingEquipment(@RequestBody userEquipment: UserEquipment): UserEquipment {
        return equipmentService.addEquipment(userEquipment)
    }

}

