package com.example.postgresql_server.model

import java.time.Instant

//去掉密碼
data class UserDto(
        var id: Int,
        var userId: String,
        var userName: String?,
        var role: String?,
        var createdAt: Instant,
        var updatedAt: Instant,
        var weaponSlot: MutableList<UserEquipment>? =null,
        var armorSlot: MutableList<UserEquipment>? =null,
        var userEquipment: MutableList<UserEquipment>?
)
//傳回前端會員裝備槽
fun sendSlotToUserDto(res: User, weaponSlot: MutableList<UserEquipment>?, armorSlot: MutableList<UserEquipment>?):UserDto{
        return UserDto(
                id = res.id,
                userId = res.userId,
                userName = res.userName,
                role = res.role,
                createdAt = res.createdAt,
                updatedAt = res.updatedAt,
                weaponSlot = weaponSlot,
                armorSlot = armorSlot,
                userEquipment = res.userEquipment,
                )
}

//傳回前端剃除密碼欄位
fun sendToUserDto(res: User):UserDto{
        return UserDto(
                id = res.id,
                userId = res.userId,
                userName = res.userName,
                role = res.role,
                createdAt = res.createdAt,
                updatedAt = res.updatedAt,
                userEquipment = res.userEquipment,

        )
}