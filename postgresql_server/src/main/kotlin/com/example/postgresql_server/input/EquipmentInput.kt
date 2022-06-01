package com.example.postgresql_server.input


data class EquipmentInput(
    val userId: Int,
    val equipmentId: Int,
    val equipmentName: String?,
)