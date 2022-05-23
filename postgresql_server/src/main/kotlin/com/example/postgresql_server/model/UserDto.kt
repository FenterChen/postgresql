package com.example.postgresql_server.model

import java.time.Instant


data class UserDto (
        var id: Int=0,
        var userId:String?= null,
        var userName:String?= null,
        var role:String?= null,
        var createdAt: Instant = Instant.now(),
        var updatedAt: Instant = Instant.now(),
)