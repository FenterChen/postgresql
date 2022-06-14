package com.wanin.rd.postgresql_server.input

import io.swagger.annotations.ApiModelProperty

data class EquipmentNameInput(
    @ApiModelProperty(notes = "裝備Id", example = "1", required = true) val equipmentId: Int,
    @ApiModelProperty(notes = "裝備名稱", required = false) val equipmentName: String?,
)