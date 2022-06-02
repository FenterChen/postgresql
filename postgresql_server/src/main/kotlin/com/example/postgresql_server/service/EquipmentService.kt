package com.example.postgresql_server.service

import com.example.postgresql_server.dataModel.EquipmentType
import com.example.postgresql_server.dataModel.UserEquipment
import com.example.postgresql_server.input.EquipmentInput
import com.example.postgresql_server.input.UserInput
import com.example.postgresql_server.repository.EquipmentByEm
import com.example.postgresql_server.repository.EquipmentRepository
import com.example.postgresql_server.repository.UserByEm
import com.example.postgresql_server.repository.UserRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class EquipmentService(
    private val equipmentByEm: EquipmentByEm,
    private val equipmentRepository: EquipmentRepository,
) {
    fun updateUserEquipment(userInput: UserInput): ResponseEntity<Int> {
        val result = equipmentByEm.insertIntoEquipmentSlot(userInput.id, userInput.weaponSlot, userInput.armorSlot)
        if (result != 0) {
            return ResponseEntity.ok().body(result)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    fun updateWeaponOrArmorName(inputEquipmentName: EquipmentInput): ResponseEntity<Int> {
        val result = equipmentByEm.updateEquipmentName(inputEquipmentName.userId,
            inputEquipmentName.equipmentId,
            inputEquipmentName.equipmentName)
        if (result != 0) {
            return ResponseEntity.ok().body(result)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    fun delByUserIdAndEquipmentId(inputEquipmentId: EquipmentInput): ResponseEntity<Int> {
        val result = equipmentByEm.delEquipment(inputEquipmentId.userId, inputEquipmentId.equipmentId)
        if (result != 0) {
            return ResponseEntity.ok().body(result)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    fun addEquipment(userEquipment: UserEquipment): UserEquipment{
        val computeValue: MutableList<EquipmentType>? =
            equipmentByEm.findEquipmentType(userEquipment.equipmentType)
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