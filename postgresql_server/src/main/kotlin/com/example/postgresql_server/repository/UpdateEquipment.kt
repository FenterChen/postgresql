package com.example.postgresql_server.repository

import com.example.postgresql_server.model.UserEquipment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.CriteriaUpdate
import javax.persistence.criteria.Root
import javax.transaction.Transactional

//criteria更新裝備狀態
@Repository
@Transactional
class UpdateEquipment {
    @PersistenceContext
    private val em: EntityManager? = null
    fun findByequipmentId(userId: Int,equipmentId: Int,userUse: Boolean){
        val cb = em!!.criteriaBuilder
        val criteriaUpdate: CriteriaUpdate<UserEquipment> = cb.createCriteriaUpdate(UserEquipment::class.java)
        val root: Root<UserEquipment> = criteriaUpdate.from(UserEquipment::class.java)

        criteriaUpdate.where(
            cb.equal(root.get<Int>("userId"), userId),
            cb.equal(root.get<Int>("equipmentId"), equipmentId)
        )//where :
        criteriaUpdate.set("userUse", userUse)///set value

        em.createQuery(criteriaUpdate).executeUpdate()
    }
}
