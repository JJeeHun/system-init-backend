package com.example.systeminitbackend.presentation.menu

import com.example.systeminitbackend.application.menu.CreateMenuCommand
import com.example.systeminitbackend.application.menu.UpdateMenuCommand
import com.example.systeminitbackend.domain.layer0.menu.MenuScope
import com.example.systeminitbackend.domain.layer0.menu.MenuType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size

data class CreateMenuRequest(
    val parentId: Long? = null,
    @field:NotBlank @field:Size(max = 50)
    val code: String,
    @field:NotBlank @field:Size(max = 50)
    val name: String,
    @field:Size(max = 100)
    val labelKey: String? = null,
    val type: MenuType,
    @field:Size(max = 200)
    val path: String? = null,
    @field:Size(max = 50)
    val icon: String? = null,
    @field:PositiveOrZero
    val sortOrder: Int = 0,
    val scope: MenuScope,
    val enabled: Boolean = true,
) {
    fun toCommand() = CreateMenuCommand(
        parentId = parentId,
        code = code,
        name = name,
        labelKey = labelKey,
        type = type,
        path = path,
        icon = icon,
        sortOrder = sortOrder,
        scope = scope,
        enabled = enabled,
    )
}

data class UpdateMenuRequest(
    val parentId: Long? = null,
    @field:NotBlank @field:Size(max = 50)
    val name: String,
    @field:Size(max = 100)
    val labelKey: String? = null,
    val type: MenuType,
    @field:Size(max = 200)
    val path: String? = null,
    @field:Size(max = 50)
    val icon: String? = null,
    @field:PositiveOrZero
    val sortOrder: Int = 0,
    val scope: MenuScope,
    val enabled: Boolean = true,
) {
    fun toCommand() = UpdateMenuCommand(
        parentId = parentId,
        name = name,
        labelKey = labelKey,
        type = type,
        path = path,
        icon = icon,
        sortOrder = sortOrder,
        scope = scope,
        enabled = enabled,
    )
}
