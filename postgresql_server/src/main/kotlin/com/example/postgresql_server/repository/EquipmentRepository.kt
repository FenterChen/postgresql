package com.example.postgresql_server.repository

import com.example.postgresql_server.model.EquipmentType
import com.example.postgresql_server.model.UserEquipment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository//criteria沒有insert語句，使用JpaRepository鍛造裝備
interface EquipmentRepository : JpaRepository<UserEquipment, Int>


