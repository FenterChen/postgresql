package com.example.postgresql_server.repository

import com.example.postgresql_server.dataModel.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.Instant
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Repository
interface UserRepository : JpaRepository<User, Int> {
    fun findAllByUserIdAndPassword(userId: String, password: String): User
    fun findByUserId(userId: String): User
}

//更新會員資料
@Repository
class UserByEm {
    @PersistenceContext
    private lateinit var em: EntityManager

    @Transactional
    fun updateByUserId(id: Int, userName: String?, role: String?): User {
        val user: User = em.find(User::class.java, id)
        user.role = role
        user.userName = userName
        user.updatedAt = Instant.now()
        return em.merge(user)
    }

}