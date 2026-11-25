package com.example.systeminitbackend.application.commoncode

import com.example.systeminitbackend.domain.layer0.commoncode.CommonCode
import com.example.systeminitbackend.domain.layer0.commoncode.CommonCodeGroup

data class CommonCodeGroupResult(
    val id: Long,
    val code: String,
    val name: String,
    val description: String?,
    val sortOrder: Int,
    val enabled: Boolean,
) {
    companion object {
        fun from(group: CommonCodeGroup): CommonCodeGroupResult = CommonCodeGroupResult(
            id = group.id!!,
            code = group.code,
            name = group.name,
            description = group.description,
            sortOrder = group.sortOrder,
            enabled = group.enabled,
        )
    }
}

data class CommonCodeResult(
    val id: Long,
    val groupId: Long,
    val code: String,
    val name: String,
    val description: String?,
    val sortOrder: Int,
    val enabled: Boolean,
) {
    companion object {
        fun from(commonCode: CommonCode): CommonCodeResult = CommonCodeResult(
            id = commonCode.id!!,
            groupId = commonCode.group.id!!,
            code = commonCode.code,
            name = commonCode.name,
            description = commonCode.description,
            sortOrder = commonCode.sortOrder,
            enabled = commonCode.enabled,
        )
    }
}
