package com.example.postgresql_server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PostgresqlServerApplication

fun main(args: Array<String>) {
	runApplication<PostgresqlServerApplication>(*args)
}

