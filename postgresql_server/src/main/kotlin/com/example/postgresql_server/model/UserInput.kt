package com.example.postgresql_server.model

data class UserInput (
    val userId:String="",
    val userName:String?="",
    val role:String?="",
    val weaponSlot: Int=0,
    val armorSlot: Int=0,
    )
