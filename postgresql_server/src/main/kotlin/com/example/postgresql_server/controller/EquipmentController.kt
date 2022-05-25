package com.example.postgresql_server.controller

import com.example.postgresql_server.model.*
import com.example.postgresql_server.repository.EquipmentRepository
import com.example.postgresql_server.repository.UpdateEquipment
import org.springframework.web.bind.annotation.*




@RestController
//@CrossOrigin("http://localhost:3030")建置後
@CrossOrigin("http://localhost:8081")
@RequestMapping("/equipment")
class EquipmentController(private val updateEquipment: UpdateEquipment,private val equipmentRepository:EquipmentRepository) {

    //更新裝備狀態
    @PutMapping("/useEquipment")
    fun useEquipment(@RequestBody userSave: UserSave) {
        return updateEquipment.findByequipmentId(userSave.id,userSave.equipmentId,userSave.userUse)
    }

    //鍛造裝備
//    @PostMapping("/forgingEquipment")
//    fun forgingEquipment(@RequestBody userSave: UserSave): UserEquipment {
//        return forgingEquipmentRepository.findByEquipmentType(userSave.equipmentType)
//    }
//        //random 屬性值
//        if (tq.resultList[0].id == 1){
//            tq.resultList[0].equipmentBasicAtk
//        }else if(tq.resultList[0].id == 2){
//            tq.resultList[0].equipmentBasicDef
//        }else{
//
//        }
}

