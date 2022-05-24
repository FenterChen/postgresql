package com.example.postgresql_server.model

import java.time.Instant

//去掉密碼
data class UserIdDto(var userId: String)
//傳回前端剃除密碼欄位