package com.wanin.rd.postgresql_server.input

import io.swagger.annotations.ApiModelProperty

class UserInfoInput(
    @ApiModelProperty(notes = "使用者Id", example = "1", required = true) val id: Int = 0,
    @ApiModelProperty(notes = "使用者名稱", example = "test", required = false) val userName: String? = "",
    @ApiModelProperty(notes = "使用者角色", example = "Ninja", required = false) val role: String? = "",
)