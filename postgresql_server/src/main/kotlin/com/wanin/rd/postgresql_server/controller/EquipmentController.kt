package com.wanin.rd.postgresql_server.controller


import com.wanin.rd.postgresql_server.dataModel.UserEquipment
import com.wanin.rd.postgresql_server.service.EquipmentService
import com.wanin.rd.postgresql_server.input.EquipmentNameInput
import com.wanin.rd.postgresql_server.input.NewEquipmentInput
import com.wanin.rd.postgresql_server.input.UserSlotInput
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

//https://192.168.1.95:8080/swagger-ui/index.html
@Api(tags = ["EquipmentController"], description = "裝備")
@RestController
@RequestMapping("/equipment")
class EquipmentController(
    private val equipmentService: EquipmentService,
) {
    @ApiOperation("裝上裝備槽")
    //id: Int,weaponSlot: String?,armorSlot: String?
    @PutMapping("/useEquipment")
    fun updateByEquipmentSlot(@RequestBody userSlotInput: UserSlotInput): ResponseEntity<Int> {
        try {
            return equipmentService.updateByEquipmentSlot(userSlotInput)
        } catch (exception: Exception) {
            throw ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, exception.message)
        }
    }

    @ApiOperation("裝備名稱修改")
    //userId: String,equipmentId: Int,equipmentName: String?
    @PutMapping("/equipmentName")
    fun updateByWeaponOrArmorName(@RequestBody inputEquipmentName: EquipmentNameInput): ResponseEntity<Int> {
        try {
            return equipmentService.updateByWeaponOrArmorName(inputEquipmentName)
        } catch (exception: Exception) {
            throw ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, exception.message)
        }
    }

    @ApiOperation("刪除裝備")
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

    @ApiOperation("鍛造裝備")
    // userId: Int,equipmentType: Int,equipmentName: String?
    @PostMapping("/addEquipment")
    fun addEquipment(@RequestBody newEquipmentInput: NewEquipmentInput): UserEquipment {
        try {
            return equipmentService.addEquipment(newEquipmentInput)
        } catch (exception: Exception) {
            throw ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, exception.message)
        }
    }

}

