package com.example.systeminitbackend.presentation.accountmenu

import com.example.systeminitbackend.application.accountmenu.CreateAccountMenuCommand
import com.example.systeminitbackend.application.accountmenu.UpdateAccountMenuCommand

data class CreateAccountMenuRequest(
    val accountId: Long,
    val menuId: Long,
    val canView: Boolean = true,
    val canCreate: Boolean = false,
    val canUpdate: Boolean = false,
    val canDelete: Boolean = false,
) {
    fun toCommand() = CreateAccountMenuCommand(
        accountId = accountId,
        menuId = menuId,
        canView = canView,
        canCreate = canCreate,
        canUpdate = canUpdate,
        canDelete = canDelete,
    )
}

data class UpdateAccountMenuRequest(
    val canView: Boolean,
    val canCreate: Boolean,
    val canUpdate: Boolean,
    val canDelete: Boolean,
) {
    fun toCommand() = UpdateAccountMenuCommand(
        canView = canView,
        canCreate = canCreate,
        canUpdate = canUpdate,
        canDelete = canDelete,
    )
}
