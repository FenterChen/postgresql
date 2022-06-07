package com.wanin.rd.postgresql_server.config

import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletResponse


class CORSFilter : GenericFilterBean() {
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val resp = servletResponse as HttpServletResponse
        resp.setHeader("Access-Control-Allow-Credentials", "true")
        resp.setHeader("Access-Control-Allow-Origin", "*")
        resp.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE, HEAD, PATCH")
        resp.setHeader("Access-Control-Max-Age", "0")
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type")
        filterChain.doFilter(servletRequest, resp)
    }
}
