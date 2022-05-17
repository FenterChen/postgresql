package com.example.postgresql_server.controller

import com.example.postgresql_server.model.User
import com.example.postgresql_server.services.Query
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("http://localhost:3030")
class AppsController(){
    @Autowired
    val jdbcTemplate: JdbcTemplate?=null
    @GetMapping("/")
    fun helloWord(): MutableList<User>? {
        return Query().getalluser(jdbcTemplate)
    }
    @PostMapping("/user")
    fun executeselectuser(user_id: String,password: String): Any? {
      val result=Query().selectuser(jdbcTemplate,user_id,password)
        if (result!!.size==0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }else{
            return result
        }
    }

}


