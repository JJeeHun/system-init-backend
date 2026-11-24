package com.example.systeminitbackend.application.menu

import com.example.systeminitbackend.domain.Menu
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MenuService(
    private val menuRepository: MenuRepository,
) {

    @Transactional
    fun create(command: CreateMenuCommand): MenuResult {
        val menu = Menu(
            parent = command.parentId?.let(::findMenu),
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
        return MenuResult.from(menuRepository.save(menu))
    }

    fun get(id: Long): MenuResult = MenuResult.from(findMenu(id))

    fun getAll(): List<MenuResult> = menuRepository.findAllByDeletedFalse().map(MenuResult::from)

    @Transactional
    fun update(id: Long, command: UpdateMenuCommand): MenuResult {
        val menu = findMenu(id)
        menu.update(
            parent = command.parentId?.let(::findMenu),
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
        findMenu(id).delete()
    }

    private fun findMenu(id: Long): Menu =
        menuRepository.findByIdAndDeletedFalse(id) ?: throw NoSuchElementException("메뉴를 찾을 수 없습니다. id=$id")
}
