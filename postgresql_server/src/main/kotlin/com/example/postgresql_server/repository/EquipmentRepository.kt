package com.example.postgresql_server.repository

import com.example.postgresql_server.input.EquipmentType
import com.example.postgresql_server.input.User
import com.example.postgresql_server.input.UserEquipment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.Instant
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery
import javax.persistence.criteria.*
import javax.transaction.Transactional


@Repository
interface EquipmentRepository : JpaRepository<UserEquipment, Int>

//criteria
@Repository
class MulEquipmentRepository {
    @PersistenceContext
    private lateinit var em: EntityManager

    //查詢裝備表並取初始值
    fun forgingEquipment(id:Int): MutableList<EquipmentType>? {
        val cb = em.criteriaBuilder
        val cq: CriteriaQuery<EquipmentType> = cb.createQuery(EquipmentType::class.java) // select UserEquipment
        val root: Root<EquipmentType> = cq.from(EquipmentType::class.java) // from UserEquipment

        val id: Predicate = cb.equal(root.get<Int>("id"), id)

        cq.where(id) // where id = :id
        val tq: TypedQuery<EquipmentType> = em.createQuery(cq)
        return tq.resultList
    }

    //查詢武器與裝備槽
    fun equipmentSlot(slot:Int?): MutableList<UserEquipment>? {
        val cb = em.criteriaBuilder
        val cq: CriteriaQuery<UserEquipment> = cb.createQuery(UserEquipment::class.java) // select UserEquipment
        val root: Root<UserEquipment> = cq.from(UserEquipment::class.java) // from UserEquipment

        val slot: Predicate = cb.equal(root.get<Int>("equipmentId"), slot)

        cq.where(slot) // where equipment_id = :slot
        val tq: TypedQuery<UserEquipment> = em.createQuery<UserEquipment>(cq)
        return tq.resultList
    }

    //修改武器與裝備名稱
    @Transactional
    fun equipmentName(userId:Int,equipmentId:Int,equipmentName:String) {
        val cb = em.criteriaBuilder
        val criteriaUpdate: CriteriaUpdate<UserEquipment> = cb.createCriteriaUpdate(UserEquipment::class.java)
        val root: Root<UserEquipment> = criteriaUpdate.from(UserEquipment::class.java)

        criteriaUpdate.where(
            cb.equal(root.get<Int>("userId"), userId),
            cb.equal(root.get<Int>("equipmentId"), equipmentId),
        )//where userId :userId && equipmentId :equipmentId

        criteriaUpdate.set("equipmentName", equipmentName)//set value

        em.createQuery(criteriaUpdate).executeUpdate()
    }
    @Transactional
    //刪除裝備
    fun delEquipment(userId: Int, equipmentId: Int): Int {
        val cb = em.criteriaBuilder
        val criteriaDelete: CriteriaDelete<UserEquipment> = cb.createCriteriaDelete(UserEquipment::class.java)
        val root: Root<UserEquipment> = criteriaDelete.from(UserEquipment::class.java)

        criteriaDelete.where(
            cb.equal(root.get<Int>("userId"), userId),
            cb.equal(root.get<Int>("equipmentId"), equipmentId),
        )//where userId :userId && equipmentId :equipmentId


        return em.createQuery(criteriaDelete).executeUpdate()
    }

    //裝上裝備槽
    fun useEquipment(userId: String,weaponSlot: Int?,armorSlot: Int? ) {
        val cb = em.criteriaBuilder
        val criteriaUpdate: CriteriaUpdate<User> = cb.createCriteriaUpdate(User::class.java)
        val root: Root<User> = criteriaUpdate.from(User::class.java)

        criteriaUpdate.where(
            cb.equal(root.get<String>("userId"), userId),
        )//where userId :userId

        criteriaUpdate.set("weaponSlot", weaponSlot)//set value
        criteriaUpdate.set("armorSlot", armorSlot)//set value
        val updatedAt = Instant.now()
        criteriaUpdate.set("updatedAt", updatedAt)//set value

        println(em.createQuery(criteriaUpdate).executeUpdate())

    }
}
