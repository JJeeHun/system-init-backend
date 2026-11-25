package com.example.systeminitbackend.application.menu

import com.example.systeminitbackend.domain.layer0.menu.MenuScope
import com.example.systeminitbackend.domain.layer0.menu.MenuType

data class CreateMenuCommand(
    val parentId: Long?,
    val code: String,
    val name: String,
    val labelKey: String?,
    val type: MenuType,
    val path: String?,
    val icon: String?,
    val sortOrder: Int,
    val scope: MenuScope,
    val enabled: Boolean,
)

data class UpdateMenuCommand(
    val parentId: Long?,
    val name: String,
    val labelKey: String?,
    val type: MenuType,
    val path: String?,
    val icon: String?,
    val sortOrder: Int,
    val scope: MenuScope,
    val enabled: Boolean,
)
