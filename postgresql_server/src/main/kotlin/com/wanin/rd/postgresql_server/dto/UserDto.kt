package com.wanin.rd.postgresql_server.dto

import com.wanin.rd.postgresql_server.dataModel.UserEquipment
import java.time.Instant

//傳回前端去掉密碼
data class UserDto(
    var id: Int,
    var userId: String,
    var userName: String?,
    var role: String?,
    var createdAt: Instant,
    var updatedAt: Instant,
    var weaponSlot: UserEquipment? = null,
    var armorSlot: UserEquipment? = null,
    var userEquipment: List<UserEquipment>?,
)

