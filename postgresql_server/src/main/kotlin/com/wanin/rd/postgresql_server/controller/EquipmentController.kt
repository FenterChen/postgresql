package com.wanin.rd.postgresql_server.controller


import com.wanin.rd.postgresql_server.dataModel.UserEquipment
import com.wanin.rd.postgresql_server.service.EquipmentService
import com.wanin.rd.postgresql_server.input.EquipmentNameInput
import com.wanin.rd.postgresql_server.input.NewEquipmentInput
import com.wanin.rd.postgresql_server.input.UserSlotInput
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

//https://*/swagger-ui/index.html
@Api(tags = ["EquipmentController"], description = "裝備")
@RestController
@RequestMapping("/equipment")
class EquipmentController(
    private val equipmentService: EquipmentService,
) {
    @ApiOperation("裝上裝備槽")
    @PutMapping("/useEquipment")//id: Int,weaponSlot: String?,armorSlot: String?
    fun updateByEquipmentSlot(
        @ApiParam(value = "裝上裝備槽所需參數") @RequestBody userSlotInput: UserSlotInput,
    ): ResponseEntity<Int> {
        try {
            return equipmentService.updateByEquipmentSlot(userSlotInput)
        } catch (exception: Exception) {
            throw ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, exception.message)
        }
    }

    @ApiOperation("裝備名稱修改")
    @PutMapping("/equipmentName")//userId: String,equipmentId: Int,equipmentName: String?
    fun updateByWeaponOrArmorName(
        @ApiParam(value = "裝備名稱修改所需參數") @RequestBody inputEquipmentName: EquipmentNameInput,
    ): ResponseEntity<Int> {
        try {
            return equipmentService.updateByWeaponOrArmorName(inputEquipmentName)
        } catch (exception: Exception) {
            throw ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, exception.message)
        }
    }

    @ApiOperation("刪除裝備")
    @ApiImplicitParam(name = "equipmentId",
        value = "裝備Id",
        required = true,
        paramType = "path",
        example = "1"
    )
    @DeleteMapping("/{equipmentId}")//equipmentId: Int
    fun delByEquipmentId(@ApiParam(value = "刪除裝備所需參數") @PathVariable equipmentId: Int): ResponseEntity<Int> {
        try {
            return equipmentService.delByEquipmentId(equipmentId)
        } catch (exception: Exception) {
            throw ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, exception.message)
        }
    }

    @ApiOperation("鍛造裝備")
    @PostMapping("/addEquipment")// userId: Int,equipmentType: Int,equipmentName: String?
    fun addEquipment(@ApiParam(value = "鍛造裝備所需參數") @RequestBody newEquipmentInput: NewEquipmentInput): UserEquipment {
        try {
            return equipmentService.addEquipment(newEquipmentInput)
        } catch (exception: Exception) {
            throw ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, exception.message)
        }
    }

}

