package com.example.postgresql_server.input



import java.time.Instant
import java.time.Instant.now
import javax.persistence.*


@Entity
@Table(name="game_user")
data class User(
    @Id @GeneratedValue val id: Int,
    @Column(name="user_id") val userId: String,
    @Column(name="user_name") var userName: String?,
    @Column var password: String,
    @Column var role: String?,
    @Column(name="created_at") val createdAt: Instant= now(),
    @Column(name="updated_at") var updatedAt: Instant= now(),

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name="weapon_slot")
    val weaponSlotList: UserEquipment?,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name="armor_slot")
    val armorSlotList: UserEquipment?,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, mappedBy = "userId")
    @OrderBy("equipmentId")
    val userEquipment: MutableList<UserEquipment>?
)



