package com.example.systeminitbackend.domain.layer1.accountmenu

import com.example.systeminitbackend.domain.layer0.account.Account
import com.example.systeminitbackend.domain.layer0.menu.Menu
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Service

@Service
class AccountMenuService(
    private val accountMenuRepository: AccountMenuRepository,
) {

    fun create(
        account: Account,
        menu: Menu,
        canView: Boolean,
        canCreate: Boolean,
        canUpdate: Boolean,
        canDelete: Boolean,
    ): AccountMenu {
        val accountMenu = AccountMenu(
            account = account,
            menu = menu,
            canView = canView,
            canCreate = canCreate,
            canUpdate = canUpdate,
            canDelete = canDelete,
        )
        return accountMenuRepository.save(accountMenu)
    }

    fun getById(id: Long): AccountMenu =
        accountMenuRepository.findByIdAndDeletedFalse(id)
            ?: throw NoSuchElementException("계정 메뉴 권한을 찾을 수 없습니다. id=$id")

    fun getAll(pageable: Pageable): Slice<AccountMenu> = accountMenuRepository.findAllByDeletedFalse(pageable)

    fun count(): Long = accountMenuRepository.countByDeletedFalse()

    fun changePermissions(
        id: Long,
        canView: Boolean,
        canCreate: Boolean,
        canUpdate: Boolean,
        canDelete: Boolean,
    ): AccountMenu {
        val accountMenu = getById(id)
        accountMenu.changePermissions(canView, canCreate, canUpdate, canDelete)
        return accountMenu
    }

    fun delete(id: Long) {
        getById(id).delete()
    }
}
