package com.example.postgresql_server.dto

import com.example.postgresql_server.input.User
import com.example.postgresql_server.input.UserEquipment
import java.time.Instant

//去掉密碼
data class UserDto(
        var id: Int,
        var userId: String,
        var userName: String?,
        var role: String?,
        var createdAt: Instant,
        var updatedAt: Instant,
        var weaponSlot: UserEquipment? = null,
        var armorSlot: UserEquipment? = null,
        var userEquipment: MutableList<UserEquipment>?,
)
//傳回前端會員裝備槽
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

