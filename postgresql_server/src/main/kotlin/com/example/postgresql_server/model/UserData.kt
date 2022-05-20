package com.example.postgresql_server.model

import javax.persistence.*

@Entity
@Table(name="game_user")
data class User(
    @Id @GeneratedValue var id: Int,
    @Column(unique = true) val userId: String,
    @Column val password: String,
    @Column val userName: String?,
    @Column val role: String?,
    @Column val createdAt: String,
    @Column val updatedAt: String)
