package com.wanin.rd.postgresql_server.controller


import com.wanin.rd.postgresql_server.dataModel.UserEquipment
import com.wanin.rd.postgresql_server.service.EquipmentService
import com.wanin.rd.postgresql_server.input.EquipmentInput
import com.wanin.rd.postgresql_server.input.UserInput
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/equipment")
class EquipmentController(
    private val equipmentService: EquipmentService,
) {
    //裝上裝備槽
    //id:Int,weaponSlot:String?,armorSlot:String?
    @PutMapping("/useEquipment")
    fun updateByEquipmentSlot(@RequestBody userInput: UserInput): ResponseEntity<Int> {
        return equipmentService.updateByEquipmentSlot(userInput)
    }

    //裝備名稱修改
    //userId:String,equipmentId:Int,equipmentName:String?
    @PutMapping("/equipmentName")
    fun updateByWeaponOrArmorName(@RequestBody inputEquipmentName: EquipmentInput): ResponseEntity<Int> {
        return equipmentService.updateByWeaponOrArmorName(inputEquipmentName)
    }

    //刪除裝備
    @DeleteMapping("/{equipmentId}")
    fun delByEquipmentId(@PathVariable equipmentId:Int): ResponseEntity<Int> {
        return equipmentService.delByEquipmentId(equipmentId)
    }

    //鍛造裝備
    @PostMapping("/forgingEquipment")
    fun addEquipment(@RequestBody userEquipment: UserEquipment): UserEquipment {
        return equipmentService.addEquipment(userEquipment)
    }

}

