package com.wanin.rd.postgresql_server.repository

import com.wanin.rd.postgresql_server.dataModel.EquipmentType
import com.wanin.rd.postgresql_server.dataModel.User
import com.wanin.rd.postgresql_server.dataModel.UserEquipment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery
import javax.persistence.criteria.*
import javax.transaction.Transactional

@Repository
interface EquipmentRepository : JpaRepository<UserEquipment, Int>

//criteria
@Repository
class EquipmentByEm {
    @PersistenceContext
    private lateinit var em: EntityManager

    //裝上裝備槽
    @Transactional
    fun updateEquipmentSlot(id: Int, weaponSlot: Int?, armorSlot: Int?): Int {
        val cb = em.criteriaBuilder
        val criteriaUpdate: CriteriaUpdate<User> = cb.createCriteriaUpdate(User::class.java)
        val root: Root<User> = criteriaUpdate.from(User::class.java)

        criteriaUpdate.where(
            cb.equal(root.get<Int>("id"), id),
        )//where userId :userId

        criteriaUpdate.set("weaponSlot", weaponSlot)//set value
        criteriaUpdate.set("armorSlot", armorSlot)//set value

        return em.createQuery(criteriaUpdate).executeUpdate()
    }

    //查詢裝備表並取初始值
    fun findEquipmentType(id: Int): MutableList<EquipmentType>? {
        val cb = em.criteriaBuilder
        val cq: CriteriaQuery<EquipmentType> = cb.createQuery(EquipmentType::class.java) // select UserEquipment
        val root: Root<EquipmentType> = cq.from(EquipmentType::class.java) // from UserEquipment

        val userid: Predicate = cb.equal(root.get<Int>("id"), id)

        cq.where(userid) // where id = :id
        val tq: TypedQuery<EquipmentType> = em.createQuery(cq)
        return tq.resultList
    }

    //修改武器與裝備名稱
    @Transactional
    fun updateEquipmentName(equipmentId: Int, equipmentName: String?): Int {
        val cb = em.criteriaBuilder
        val criteriaUpdate: CriteriaUpdate<UserEquipment> = cb.createCriteriaUpdate(UserEquipment::class.java)
        val root: Root<UserEquipment> = criteriaUpdate.from(UserEquipment::class.java)

        criteriaUpdate.where(
            cb.equal(root.get<Int>("equipmentId"), equipmentId),
        )//where equipmentId :equipmentId

        criteriaUpdate.set("equipmentName", equipmentName)//set value

        return em.createQuery(criteriaUpdate).executeUpdate()
    }

    @Transactional
    //刪除裝備
    fun delEquipment(equipmentId: Int): Int {
        val cb = em.criteriaBuilder
        val criteriaDelete: CriteriaDelete<UserEquipment> = cb.createCriteriaDelete(UserEquipment::class.java)
        val root: Root<UserEquipment> = criteriaDelete.from(UserEquipment::class.java)

        criteriaDelete.where(
            cb.equal(root.get<Int>("equipmentId"), equipmentId),
        )// equipmentId :equipmentId


        return em.createQuery(criteriaDelete).executeUpdate()
    }
}


