package com.example.postgresql_server.model

data class User
    (val id: Int, val user_id: String,val password: String,
     val user_name: String?,val role: String?,val created_at: String,
     val updated_at: String)