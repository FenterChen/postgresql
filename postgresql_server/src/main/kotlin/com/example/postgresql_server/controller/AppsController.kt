package com.example.postgresql_server.controller

import com.example.postgresql_server.model.User
import com.example.postgresql_server.services.Query
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AppsController(){
    @Autowired
    val jdbcTemplate: JdbcTemplate?=null
    @GetMapping("/")
    fun helloWord(): MutableList<User>? {
        return Query().getalluser(jdbcTemplate)
    }
    @PostMapping("/user")
    fun executeselectuser(user_id: String,password: String): MutableList<User>? {
     return Query().selectuser(jdbcTemplate,user_id,password)
    }

}



