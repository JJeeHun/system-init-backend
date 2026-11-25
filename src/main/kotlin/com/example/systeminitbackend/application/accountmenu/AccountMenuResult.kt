package com.example.systeminitbackend.application.accountmenu

import com.example.systeminitbackend.domain.layer1.accountmenu.AccountMenu

data class AccountMenuResult(
    val id: Long,
    val accountId: Long,
    val menuId: Long,
    val canView: Boolean,
    val canCreate: Boolean,
    val canUpdate: Boolean,
    val canDelete: Boolean,
) {
    companion object {
        fun from(accountMenu: AccountMenu): AccountMenuResult = AccountMenuResult(
            id = accountMenu.id!!,
            accountId = accountMenu.account.id!!,
            menuId = accountMenu.menu.id!!,
            canView = accountMenu.canView,
            canCreate = accountMenu.canCreate,
            canUpdate = accountMenu.canUpdate,
            canDelete = accountMenu.canDelete,
        )
    }
}
