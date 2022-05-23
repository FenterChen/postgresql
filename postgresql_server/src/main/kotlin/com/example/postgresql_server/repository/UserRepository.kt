package com.example.postgresql_server.repository

import com.example.postgresql_server.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository : JpaRepository<User, Int>{
    fun findAllByUserIdAndPassword(userId: String, password: String): List<User>
    fun findByUserId(userId: String): List<User>

}