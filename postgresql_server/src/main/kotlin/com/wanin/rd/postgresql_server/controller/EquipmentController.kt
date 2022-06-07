package com.wanin.rd.postgresql_server.controller


import com.wanin.rd.postgresql_server.dataModel.UserEquipment
import com.wanin.rd.postgresql_server.service.EquipmentService
import com.wanin.rd.postgresql_server.input.EquipmentInput
import com.wanin.rd.postgresql_server.input.UserInput
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/equipment")
class EquipmentController(
    private val equipmentService: EquipmentService,
) {
    //裝上裝備槽
    //id: Int,weaponSlot: String?,armorSlot: String?
    @PutMapping("/useEquipment")
    fun updateByEquipmentSlot(@RequestBody userInput: UserInput): ResponseEntity<Int> {
        try {
            return equipmentService.updateByEquipmentSlot(userInput)
        } catch (exception: Exception) {
            throw ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, exception.message)
        }
    }

    //裝備名稱修改
    //userId: String,equipmentId: Int,equipmentName: String?
    @PutMapping("/equipmentName")
    fun updateByWeaponOrArmorName(@RequestBody inputEquipmentName: EquipmentInput): ResponseEntity<Int> {
        try {
            return equipmentService.updateByWeaponOrArmorName(inputEquipmentName)
        } catch (exception: Exception) {
            throw ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, exception.message)
        }
    }

    //刪除裝備
    //equipmentId: Int
    @DeleteMapping("/{equipmentId}")
    fun delByEquipmentId(@PathVariable equipmentId: Int): ResponseEntity<Int> {
        try {
            return equipmentService.delByEquipmentId(equipmentId)
        } catch (exception: Exception) {
            throw ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, exception.message)
        }
    }

    //鍛造裝備
    // userId: Int,equipmentType: Int,equipmentName: String?
    @PostMapping("/forgingEquipment")
    fun addEquipment(@RequestBody userEquipment: UserEquipment): UserEquipment {
        try {
            return equipmentService.addEquipment(userEquipment)
        } catch (exception: Exception) {
            throw ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, exception.message)
        }
    }

}

