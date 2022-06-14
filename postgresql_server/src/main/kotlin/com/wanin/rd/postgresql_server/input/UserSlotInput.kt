package com.wanin.rd.postgresql_server.input

import io.swagger.annotations.ApiModelProperty

data class UserSlotInput(
    @ApiModelProperty(notes = "使用者Id", example = "1", required = true) val id: Int = 0,
    @ApiModelProperty(notes = "使用者武器槽", example = "1", required = false) val weaponSlot: Int? = null,
    @ApiModelProperty(notes = "使用者盔甲槽", example = "1", required = false) val armorSlot: Int? = null,
)
