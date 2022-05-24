package com.example.postgresql_server.model

data class UserSave (
    val userId:String="",
    val id:Int=0,
    val userName:String?=null,
    val role:String?=null,
    val equipmentId: Int=0,
    var userUse: Boolean,
    )
