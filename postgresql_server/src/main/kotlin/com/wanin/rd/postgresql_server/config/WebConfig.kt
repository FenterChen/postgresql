package com.wanin.rd.postgresql_server.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class WebConfig() {
    @Bean
    fun simpleCORSFilter(): FilterRegistrationBean<CORSFilter> {
        val registration = FilterRegistrationBean<CORSFilter>()
        registration.filter = CORSFilter()
        registration.urlPatterns = listOf("/*")
        return registration
    }
}

