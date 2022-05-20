package com.example.postgresql_server.model

import java.time.Instant
import javax.persistence.*

@Entity
@Table(name="game_user")
data class User(
    @Id @GeneratedValue val id: Int,
    @Column(unique = true) val userId: String,
    @Column val password: String,
    @Column var userName: String?,
    @Column var role: String?,
    @Column val createdAt: Instant,
    @Column val updatedAt: Instant
    )
