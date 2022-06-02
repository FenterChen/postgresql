package com.wanin.rd.postgresql_server.input

data class UserInput(
    val id: Int = 0,
    var userId: String = "",
    val userName: String = "",
    val role: String? = "",
    val weaponSlot: Int? = null,
    val armorSlot: Int? = null,
)
