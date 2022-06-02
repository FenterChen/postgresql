package com.wanin.rd.postgresql_server.dataModel

import javax.persistence.*

@Entity
@Table(name = "user_equipment", schema = "daniel")
data class UserEquipment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id") var equipmentId: Int,
    @Column(name = "user_id") val userId: Int,
    @Column(name = "equipment_name") var equipmentName: String?,
    @Column(name = "equipment_type") var equipmentType: Int,
    @Column(name = "equipment_atk") var equipmentAtk: Int,
    @Column(name = "equipment_def") var equipmentDef: Int,
)