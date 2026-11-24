package com.example.systeminitbackend.application.accountmenu

data class CreateAccountMenuCommand(
    val accountId: Long,
    val menuId: Long,
    val canView: Boolean,
    val canCreate: Boolean,
    val canUpdate: Boolean,
    val canDelete: Boolean,
)

data class UpdateAccountMenuCommand(
    val canView: Boolean,
    val canCreate: Boolean,
    val canUpdate: Boolean,
    val canDelete: Boolean,
)
