package com.example.postgresql_server.input

data class UserInput (
    val id:Int=0,
    var userId: String?="",
    val userName:String?="",
    val role:String?="",
    val weaponSlot: Int=0,
    val armorSlot: Int=0,
    )
