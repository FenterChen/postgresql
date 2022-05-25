package com.example.postgresql_server.repository

import com.example.postgresql_server.model.EquipmentType
import com.example.postgresql_server.model.User
import com.example.postgresql_server.model.UserEquipment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.CriteriaUpdate
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import javax.transaction.Transactional


@Repository
interface AddEquipmentRepository : JpaRepository<UserEquipment, Int>

//criteria
@Repository
@Transactional
class EquipmentRepository {
    @PersistenceContext
    private val em: EntityManager? = null

    // 更新裝備狀態
    fun UpdateEquipment(userId: Int,equipmentId: Int,userUse: Boolean){
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
    //查詢裝備表
    fun ForgingEquipment(id:Int): MutableList<EquipmentType>? {
        val cb = em!!.criteriaBuilder
        val cq: CriteriaQuery<EquipmentType> = cb.createQuery(EquipmentType::class.java) // select UserEquipment
        val root: Root<EquipmentType> = cq.from(EquipmentType::class.java) // from UserEquipment

        val id: Predicate = cb.equal(root.get<Int>("id"), id)

        cq.where(id) // where id = :id
        val tq: TypedQuery<EquipmentType> = em.createQuery<EquipmentType>(cq)
        return tq.resultList
    }
}
