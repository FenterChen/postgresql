package com.wanin.rd.postgresql_server.input

import io.swagger.annotations.ApiModelProperty

class NewEquipmentInput(
    @ApiModelProperty(notes = "會員Id", example = "1", required = true) val userId: Int,
    @ApiModelProperty(notes = "裝備名稱", required = false) val equipmentName: String?,
    @ApiModelProperty(notes = "裝備類型", example = "1", required = true) var equipmentType: Int,
)