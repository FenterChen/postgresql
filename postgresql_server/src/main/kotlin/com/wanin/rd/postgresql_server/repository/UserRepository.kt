package com.wanin.rd.postgresql_server.repository

import com.wanin.rd.postgresql_server.dataModel.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.CriteriaUpdate
import javax.persistence.criteria.Root
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
    fun updateByUserId(id: Int, userName: String?, role: String?): Int {

        val cb = em.criteriaBuilder
        val criteriaUpdate: CriteriaUpdate<User> = cb.createCriteriaUpdate(User::class.java)
        val root: Root<User> = criteriaUpdate.from(User::class.java)

        criteriaUpdate.where(
            cb.equal(root.get<Int>("id"), id),
        )//where userId :userId

        criteriaUpdate.set("userName", userName)//set value
        criteriaUpdate.set("role", role)//set value

        return em.createQuery(criteriaUpdate).executeUpdate()
    }

}