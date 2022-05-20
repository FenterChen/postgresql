package com.example.postgresql_server.model

import java.time.Instant

//傳回前端去密碼欄位
data class UserInfo(
    val id: Int,
    val userId:String,
    var userName:String?,
    var role:String?,
    val createdAt: Instant,
    val updatedAt:Instant,
)