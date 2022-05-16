package com.example.postgresql_server.services

import com.example.postgresql_server.model.User
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class Query() {

    //取回所有使用者
    fun getalluser(jdbcTemplate: JdbcTemplate?): MutableList<User>? {

        val gameuser: RowMapper<User> = RowMapper<User> { resultSet: ResultSet, rowIndex: Int ->
            User(
                resultSet.getInt("id"), resultSet.getString("user_id"),
                resultSet.getString("password"), resultSet.getString("user_name"),
                resultSet.getString("role"), resultSet.getString("created_at"),
                resultSet.getString("updated_at")
            )
        }
        //Query records to print out:
        return jdbcTemplate?.query("SELECT * FROM game_user", gameuser)
    }

    //使用者登入
    fun selectuser(jdbcTemplate: JdbcTemplate?, user_id: String,password: String): MutableList<User>? {

        val gameuser: RowMapper<User> = RowMapper<User> { resultSet: ResultSet, rowIndex: Int ->
            User(
                resultSet.getInt("id"), resultSet.getString("user_id"),
                resultSet.getString("password"), resultSet.getString("user_name"),
                resultSet.getString("role"), resultSet.getString("created_at"),
                resultSet.getString("updated_at")
            )
        }
        //Query records to print out:
        return jdbcTemplate?.query("SELECT * FROM game_user WHERE user_id ='$user_id' AND password='$password'", gameuser)
    }


}