package com.example.systeminitbackend.application.accountmenu

import com.example.systeminitbackend.application.common.CountResult
import com.example.systeminitbackend.application.common.SliceResult
import com.example.systeminitbackend.domain.layer0.account.AccountService
import com.example.systeminitbackend.domain.layer0.menu.MenuService
import com.example.systeminitbackend.domain.layer1.accountmenu.AccountMenuService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccountMenuFacade(
    private val accountMenuService: AccountMenuService,
    private val accountService: AccountService,
    private val menuService: MenuService,
) {

    @Transactional
    fun create(command: CreateAccountMenuCommand): AccountMenuResult {
        val account = accountService.getById(command.accountId)
        val menu = menuService.getById(command.menuId)
        val accountMenu = accountMenuService.create(
            account = account,
            menu = menu,
            canView = command.canView,
            canCreate = command.canCreate,
            canUpdate = command.canUpdate,
            canDelete = command.canDelete,
        )
        return AccountMenuResult.from(accountMenu)
    }

    fun get(id: Long): AccountMenuResult = AccountMenuResult.from(accountMenuService.getById(id))

    fun getAll(pageable: Pageable): SliceResult<AccountMenuResult> =
        SliceResult.from(accountMenuService.getAll(pageable), AccountMenuResult::from)

    fun count(): CountResult = CountResult(accountMenuService.count())

    @Transactional
    fun update(id: Long, command: UpdateAccountMenuCommand): AccountMenuResult {
        val accountMenu = accountMenuService.changePermissions(
            id = id,
            canView = command.canView,
            canCreate = command.canCreate,
            canUpdate = command.canUpdate,
            canDelete = command.canDelete,
        )
        return AccountMenuResult.from(accountMenu)
    }

    @Transactional
    fun delete(id: Long) {
        accountMenuService.delete(id)
    }
}
