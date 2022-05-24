package com.example.postgresql_server.model


import javax.persistence.*
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaUpdate
import javax.persistence.criteria.Root


@Entity
@Table(name="user_equipment")
data class UserEquipment(
    @Id @GeneratedValue @Column(name="equipment_id")  val equipmentId: Int,
    @Column(name="user_id")  val userId: Int,
    @Column(name="equipment_name")  val equipmentName: String?,
    @Column(name="user_use")  var userUse: Boolean,
)