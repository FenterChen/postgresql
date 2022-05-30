package com.example.postgresql_server.input


import javax.persistence.*


@Entity
@Table(name="user_equipment")
data class UserEquipment(
    @Id @GeneratedValue @Column(name="equipment_id")  val equipmentId: Int,
    @Column(name="user_id")  val userId: Int,
    @Column(name="equipment_name") var equipmentName: String?,
    @Column(name="equipment_type")  var equipmentType: Int,
    @Column(name="equipment_atk")  var equipmentAtk: Int,
    @Column(name="equipment_def")  var equipmentDef: Int,
)