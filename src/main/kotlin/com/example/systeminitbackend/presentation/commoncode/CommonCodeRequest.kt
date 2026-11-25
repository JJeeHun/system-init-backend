package com.example.systeminitbackend.presentation.commoncode

import com.example.systeminitbackend.application.commoncode.CreateCommonCodeCommand
import com.example.systeminitbackend.application.commoncode.CreateCommonCodeGroupCommand
import com.example.systeminitbackend.application.commoncode.UpdateCommonCodeCommand
import com.example.systeminitbackend.application.commoncode.UpdateCommonCodeGroupCommand
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size

data class CreateCommonCodeGroupRequest(
    @field:NotBlank @field:Size(max = 50)
    val code: String,
    @field:NotBlank @field:Size(max = 50)
    val name: String,
    @field:Size(max = 255)
    val description: String? = null,
    @field:PositiveOrZero
    val sortOrder: Int = 0,
    val enabled: Boolean = true,
) {
    fun toCommand() = CreateCommonCodeGroupCommand(
        code = code,
        name = name,
        description = description,
        sortOrder = sortOrder,
        enabled = enabled,
    )
}

data class UpdateCommonCodeGroupRequest(
    @field:NotBlank @field:Size(max = 50)
    val name: String,
    @field:Size(max = 255)
    val description: String? = null,
    @field:PositiveOrZero
    val sortOrder: Int = 0,
    val enabled: Boolean = true,
) {
    fun toCommand() = UpdateCommonCodeGroupCommand(
        name = name,
        description = description,
        sortOrder = sortOrder,
        enabled = enabled,
    )
}

data class CreateCommonCodeRequest(
    val groupId: Long,
    @field:NotBlank @field:Size(max = 50)
    val code: String,
    @field:NotBlank @field:Size(max = 50)
    val name: String,
    @field:Size(max = 255)
    val description: String? = null,
    @field:PositiveOrZero
    val sortOrder: Int = 0,
    val enabled: Boolean = true,
) {
    fun toCommand() = CreateCommonCodeCommand(
        groupId = groupId,
        code = code,
        name = name,
        description = description,
        sortOrder = sortOrder,
        enabled = enabled,
    )
}

data class UpdateCommonCodeRequest(
    @field:NotBlank @field:Size(max = 50)
    val name: String,
    @field:Size(max = 255)
    val description: String? = null,
    @field:PositiveOrZero
    val sortOrder: Int = 0,
    val enabled: Boolean = true,
) {
    fun toCommand() = UpdateCommonCodeCommand(
        name = name,
        description = description,
        sortOrder = sortOrder,
        enabled = enabled,
    )
}
