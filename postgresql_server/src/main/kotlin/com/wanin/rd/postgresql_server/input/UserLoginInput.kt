package com.wanin.rd.postgresql_server.input

import io.swagger.annotations.ApiModelProperty

data class UserLoginInput(
    @ApiModelProperty(notes = "使用者帳號", example = "test", required = true) val userId: String,
    @ApiModelProperty(notes = "密碼", example = "test", required = true) val password: String,
)

