package com.example.systeminitbackend.application.menu

import com.example.systeminitbackend.domain.Menu
import com.example.systeminitbackend.domain.enums.MenuScope
import com.example.systeminitbackend.domain.enums.MenuType

data class MenuResult(
    val id: Long,
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
) {
    companion object {
        fun from(menu: Menu): MenuResult = MenuResult(
            id = menu.id!!,
            parentId = menu.parent?.id,
            code = menu.code,
            name = menu.name,
            labelKey = menu.labelKey,
            type = menu.type,
            path = menu.path,
            icon = menu.icon,
            sortOrder = menu.sortOrder,
            scope = menu.scope,
            enabled = menu.enabled,
        )
    }
}
