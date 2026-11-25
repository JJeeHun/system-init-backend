package com.example.systeminitbackend.domain.layer0.menu

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Service

@Service
class MenuService(
    private val menuRepository: MenuRepository,
) {

    fun create(
        parentId: Long?,
        code: String,
        name: String,
        labelKey: String?,
        type: MenuType,
        path: String?,
        icon: String?,
        sortOrder: Int,
        scope: MenuScope,
        enabled: Boolean,
    ): Menu {
        val menu = Menu(
            parent = parentId?.let(::getById),
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
        return menuRepository.save(menu)
    }

    fun getById(id: Long): Menu =
        menuRepository.findByIdAndDeletedFalse(id) ?: throw NoSuchElementException("메뉴를 찾을 수 없습니다. id=$id")

    fun getAll(pageable: Pageable): Slice<Menu> = menuRepository.findAllByDeletedFalse(pageable)

    fun count(): Long = menuRepository.countByDeletedFalse()

    fun update(
        id: Long,
        parentId: Long?,
        name: String,
        labelKey: String?,
        type: MenuType,
        path: String?,
        icon: String?,
        sortOrder: Int,
        scope: MenuScope,
        enabled: Boolean,
    ): Menu {
        val menu = getById(id)
        menu.update(
            parent = parentId?.let(::getById),
            name = name,
            labelKey = labelKey,
            type = type,
            path = path,
            icon = icon,
            sortOrder = sortOrder,
            scope = scope,
            enabled = enabled,
        )
        return menu
    }

    fun delete(id: Long) {
        getById(id).delete()
    }
}
