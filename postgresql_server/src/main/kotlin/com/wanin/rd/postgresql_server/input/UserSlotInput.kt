package com.wanin.rd.postgresql_server.input

data class UserSlotInput(
    val id: Int = 0,
    val weaponSlot: Int? = null,
    val armorSlot: Int? = null,
)
