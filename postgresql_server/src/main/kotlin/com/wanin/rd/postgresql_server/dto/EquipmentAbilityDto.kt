package com.wanin.rd.postgresql_server.dto

data class EquipmentAbilityDto(var equipmentAtk: Int, var equipmentDef: Int) {
    companion object {
        var equipmentDef: Int = 0
        var equipmentAtk: Int = 0
    }
}