package com.wanin.rd.postgresql_server.service

import com.wanin.rd.postgresql_server.dataModel.EquipmentType
import com.wanin.rd.postgresql_server.dataModel.UserEquipment
import com.wanin.rd.postgresql_server.dto.EquipmentAbilityDto
import com.wanin.rd.postgresql_server.input.EquipmentNameInput
import com.wanin.rd.postgresql_server.input.NewEquipmentInput
import com.wanin.rd.postgresql_server.input.UserSlotInput
import com.wanin.rd.postgresql_server.repository.EquipmentByEm
import com.wanin.rd.postgresql_server.repository.EquipmentRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class EquipmentService(
    private val equipmentByEm: EquipmentByEm,
    private val equipmentRepository: EquipmentRepository,
) {
    fun updateByEquipmentSlot(userSlotInput: UserSlotInput): ResponseEntity<Int> {
        val result =
            equipmentByEm.updateEquipmentSlot(userSlotInput.id, userSlotInput.weaponSlot, userSlotInput.armorSlot)
        if (result == 0) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        return ResponseEntity.ok().body(result)
    }

    fun updateByWeaponOrArmorName(inputEquipmentName: EquipmentNameInput): ResponseEntity<Int> {
        val result = equipmentByEm.updateEquipmentName(
            inputEquipmentName.equipmentId,
            inputEquipmentName.equipmentName)
        if (result == 0) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        return ResponseEntity.ok().body(result)
    }

    fun delByEquipmentId(equipmentId: Int): ResponseEntity<Int> {
        val result = equipmentByEm.delEquipment(equipmentId)
        if (result == 0) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        return ResponseEntity.ok().body(result)
    }

    fun addEquipment(newEquipmentInput: NewEquipmentInput): UserEquipment {
        val someComputeValue: MutableList<EquipmentType>? =
            equipmentByEm.findEquipmentType(newEquipmentInput.equipmentType)
        val attribute = (1..50).random()//屬性值跑隨機
        //產生攻擊值或防禦值
        val computeValue = checkNotNull(someComputeValue)

        when (computeValue[0].equipmentType) {
            "weapon" -> {
                EquipmentAbilityDto.equipmentAtk = attribute * computeValue[0].equipmentBasicAtk!!//攻擊屬性
                EquipmentAbilityDto.equipmentDef = 0//無防禦屬性
            }
            "armor" -> {
                EquipmentAbilityDto.equipmentDef = attribute * computeValue[0].equipmentBasicDef!!//防禦屬性
                EquipmentAbilityDto.equipmentAtk = 0//無攻擊屬性
            }
            else -> {
                throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        return equipmentRepository.save(UserEquipment(null,
            newEquipmentInput.userId,
            newEquipmentInput.equipmentName,
            newEquipmentInput.equipmentType,
            EquipmentAbilityDto.equipmentAtk,
            EquipmentAbilityDto.equipmentDef)
        )
    }

}
