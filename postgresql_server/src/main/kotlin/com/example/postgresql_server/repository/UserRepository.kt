package com.example.postgresql_server.repository

import com.example.postgresql_server.model.User
import com.example.postgresql_server.model.UserEquipment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.Instant
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.CriteriaUpdate
import javax.persistence.criteria.Root
import javax.transaction.Transactional


@Repository
interface UserRepository : JpaRepository<User, Int>{
    fun findAllByUserIdAndPassword(userId: String, password: String): User
    fun findByUserId(userId: String): User
}

//更新會員資料
@Repository
@Transactional
class UpdateUser {
    @PersistenceContext
    private val em: EntityManager? = null
    fun updateByUserId(userId: String, userName: String?, role:String?) {
        val cb = em!!.criteriaBuilder
        val criteriaUpdate: CriteriaUpdate<User> = cb.createCriteriaUpdate(User::class.java)
        val root: Root<User> = criteriaUpdate.from(User::class.java)

        criteriaUpdate.where(
            cb.equal(root.get<String>("userId"), userId),
        )//where userId :userId

        criteriaUpdate.set("userName", userName)//set value
        criteriaUpdate.set("role", role)//set value
        val updatedAt =Instant.now()
        criteriaUpdate.set("updatedAt", updatedAt)//set value

        em.createQuery(criteriaUpdate).executeUpdate()

    }
}