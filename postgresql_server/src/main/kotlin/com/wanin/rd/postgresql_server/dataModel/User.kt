package com.wanin.rd.postgresql_server.dataModel

import com.wanin.rd.postgresql_server.dto.UserDto
import java.time.Instant
import java.time.Instant.now
import javax.persistence.*

@Entity
@Table(name = "game_user", schema = "daniel")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int,
    @Column(name = "user_id") var userId: String,
    @Column(name = "user_name") var userName: String? = null,
    @Column var password: String,
    @Column var role: String? = null,
    @Column(name = "created_at") val createdAt: Instant = now(),
    @Column(name = "updated_at") var updatedAt: Instant = now(),

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "weapon_slot")
    var weaponSlot: UserEquipment? = null,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "armor_slot")
    var armorSlot: UserEquipment? = null,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, mappedBy = "userId")
    @OrderBy("equipmentId")
    val userEquipment: List<UserEquipment>?,
) {
    fun convertToUserVo() = UserDto(
        id = this.id,
        userId = this.userId,
        userName = this.userName,
        role = this.role,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        weaponSlot = this.weaponSlot,
        armorSlot = this.armorSlot,
        userEquipment = this.userEquipment,
    )
}


