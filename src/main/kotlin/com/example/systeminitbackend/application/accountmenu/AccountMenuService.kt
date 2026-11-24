package com.example.systeminitbackend.application.accountmenu

import com.example.systeminitbackend.application.account.AccountRepository
import com.example.systeminitbackend.application.menu.MenuRepository
import com.example.systeminitbackend.domain.mapping.AccountMenu
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccountMenuService(
    private val accountMenuRepository: AccountMenuRepository,
    private val accountRepository: AccountRepository,
    private val menuRepository: MenuRepository,
) {

    @Transactional
    fun create(command: CreateAccountMenuCommand): AccountMenuResult {
        val account = accountRepository.findByIdAndDeletedFalse(command.accountId)
            ?: throw NoSuchElementException("계정을 찾을 수 없습니다. id=${command.accountId}")
        val menu = menuRepository.findByIdAndDeletedFalse(command.menuId)
            ?: throw NoSuchElementException("메뉴를 찾을 수 없습니다. id=${command.menuId}")
        val accountMenu = AccountMenu(
            account = account,
            menu = menu,
            canView = command.canView,
            canCreate = command.canCreate,
            canUpdate = command.canUpdate,
            canDelete = command.canDelete,
        )
        return AccountMenuResult.from(accountMenuRepository.save(accountMenu))
    }

    fun get(id: Long): AccountMenuResult = AccountMenuResult.from(findAccountMenu(id))

    fun getAll(): List<AccountMenuResult> =
        accountMenuRepository.findAllByDeletedFalse().map(AccountMenuResult::from)

    @Transactional
    fun update(id: Long, command: UpdateAccountMenuCommand): AccountMenuResult {
        val accountMenu = findAccountMenu(id)
        accountMenu.changePermissions(command.canView, command.canCreate, command.canUpdate, command.canDelete)
        return AccountMenuResult.from(accountMenu)
    }

    @Transactional
    fun delete(id: Long) {
        findAccountMenu(id).delete()
    }

    private fun findAccountMenu(id: Long): AccountMenu =
        accountMenuRepository.findByIdAndDeletedFalse(id)
            ?: throw NoSuchElementException("계정 메뉴 권한을 찾을 수 없습니다. id=$id")
}
