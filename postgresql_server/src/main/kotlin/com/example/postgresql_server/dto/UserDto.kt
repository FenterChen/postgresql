package com.example.postgresql_server.dto

import com.example.postgresql_server.dataModel.User
import com.example.postgresql_server.dataModel.UserEquipment
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

fun userConvertDto(res: User): UserDto {
    return UserDto(
        id = res.id,
        userId = res.userId,
        userName = res.userName,
        role = res.role,
        createdAt = res.createdAt,
        updatedAt = res.updatedAt,
        weaponSlot = res.weaponSlot,
        armorSlot = res.armorSlot,
        userEquipment = res.userEquipment,
    )
}

