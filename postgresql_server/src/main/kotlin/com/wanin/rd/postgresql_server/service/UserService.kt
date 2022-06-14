package com.wanin.rd.postgresql_server.service

import com.wanin.rd.postgresql_server.dataModel.User
import com.wanin.rd.postgresql_server.dto.UserDto
import com.wanin.rd.postgresql_server.dto.UserIdDto
import com.wanin.rd.postgresql_server.repository.UserByEm
import com.wanin.rd.postgresql_server.repository.UserRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userByEm: UserByEm,
) {
    fun getUsers(): MutableList<User> =
        userRepository.findAll()

    fun getByUserIdAndPassword(userId: String, password: String): UserIdDto {
        val result = userRepository.findAllByUserIdAndPassword(userId, password)
        return UserIdDto(result.userId)//僅傳回userId
    }

    fun addUser(register: User): UserIdDto {
        try {
            userRepository.findByUserId(register.userId)
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "已有此帳號")
        } catch (exception: EmptyResultDataAccessException) {
            val result = userRepository.save(register)
            return UserIdDto(result.userId)//僅傳回userId
        }
    }

    fun findUserByUserId(userId: String): UserDto {
        val result = userRepository.findByUserId(userId)
        return result.convertToUserVo()
    }

    fun updateUserData(id: Int, userName: String?, role: String?): Int {
        return userByEm.updateByUserId(id, userName, role)
    }

}