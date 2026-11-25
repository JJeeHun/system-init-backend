package com.example.systeminitbackend.application.menu

import com.example.systeminitbackend.application.common.CountResult
import com.example.systeminitbackend.application.common.SliceResult
import com.example.systeminitbackend.domain.layer0.menu.MenuService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MenuFacade(
    private val menuService: MenuService,
) {

    @Transactional
    fun create(command: CreateMenuCommand): MenuResult {
        val menu = menuService.create(
            parentId = command.parentId,
            code = command.code,
            name = command.name,
            labelKey = command.labelKey,
            type = command.type,
            path = command.path,
            icon = command.icon,
            sortOrder = command.sortOrder,
            scope = command.scope,
            enabled = command.enabled,
        )
        return MenuResult.from(menu)
    }

    fun get(id: Long): MenuResult = MenuResult.from(menuService.getById(id))

    fun getAll(pageable: Pageable): SliceResult<MenuResult> =
        SliceResult.from(menuService.getAll(pageable), MenuResult::from)

    fun count(): CountResult = CountResult(menuService.count())

    @Transactional
    fun update(id: Long, command: UpdateMenuCommand): MenuResult {
        val menu = menuService.update(
            id = id,
            parentId = command.parentId,
            name = command.name,
            labelKey = command.labelKey,
            type = command.type,
            path = command.path,
            icon = command.icon,
            sortOrder = command.sortOrder,
            scope = command.scope,
            enabled = command.enabled,
        )
        return MenuResult.from(menu)
    }

    @Transactional
    fun delete(id: Long) {
        menuService.delete(id)
    }
}
