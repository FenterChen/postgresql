package com.wanin.rd.postgresql_server.service

import com.wanin.rd.postgresql_server.dataModel.EquipmentType
import com.wanin.rd.postgresql_server.dataModel.UserEquipment
import com.wanin.rd.postgresql_server.input.EquipmentInput
import com.wanin.rd.postgresql_server.input.UserInput
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
    fun updateByEquipmentSlot(userInput: UserInput): ResponseEntity<Int> {
        val result = equipmentByEm.updateEquipmentSlot(userInput.id, userInput.weaponSlot, userInput.armorSlot)
        if (result != 0) {
            return ResponseEntity.ok().body(result)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }

    }

    fun updateByWeaponOrArmorName(inputEquipmentName: EquipmentInput): ResponseEntity<Int> {
        val result = equipmentByEm.updateEquipmentName(
            inputEquipmentName.equipmentId,
            inputEquipmentName.equipmentName)
        if (result != 0) {
            return ResponseEntity.ok().body(result)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    fun delByEquipmentId(equipmentId: Int): ResponseEntity<Int> {
        val result = equipmentByEm.delEquipment(equipmentId)
        if (result != 0) {
            return ResponseEntity.ok().body(result)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    fun addEquipment(userEquipment: UserEquipment): UserEquipment {

        val someComputeValue: MutableList<EquipmentType>? =
            equipmentByEm.findEquipmentType(userEquipment.equipmentType)
        val attribute = (1..50).random()//屬性值跑隨機

        //產生攻擊值或防禦值
        val computeValue = checkNotNull(someComputeValue)
        when (computeValue[0].equipmentType) {
            "weapon" -> {
                userEquipment.equipmentAtk = attribute * computeValue[0].equipmentBasicAtk!!//攻擊屬性
            }
            "armor" -> {
                userEquipment.equipmentDef = attribute * computeValue[0].equipmentBasicDef!!//防禦屬性
            }
            else -> {
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, "鍛造錯誤")
            }
        }

        //存入裝備名稱、寫入資料庫並傳回前端
        userEquipment.equipmentName = userEquipment.equipmentName//裝備名稱
        return equipmentRepository.save(userEquipment)
    }

}

