package com.wanin.rd.postgresql_server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class PostgresqlServerApplication

fun main(args: Array<String>) {
    runApplication<PostgresqlServerApplication>(*args)
}



