package com.example.postgresql_server.dataModel

import javax.persistence.*


@Entity
@Table(name = "equipment_type")
data class EquipmentType(
    @Id @GeneratedValue @Column(name = "id") val id: Int,
    @Column(name = "equipment_type") val equipmentType: String,
    @Column(name = "equipment_basic_atk") val equipmentBasicAtk: Int?,
    @Column(name = "equipment_basic_def") val equipmentBasicDef: Int?,
)