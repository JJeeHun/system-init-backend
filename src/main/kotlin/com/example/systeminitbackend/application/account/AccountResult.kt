package com.example.systeminitbackend.application.account

import com.example.systeminitbackend.domain.layer0.account.Account

data class AccountResult(
    val id: Long,
    val loginId: String,
    val name: String,
    val email: String,
    val centerCode: String,
    val enabled: Boolean,
) {
    companion object {
        fun from(account: Account): AccountResult = AccountResult(
            id = account.id!!,
            loginId = account.loginId,
            name = account.name,
            email = account.email,
            centerCode = account.centerCode,
            enabled = account.enabled,
        )
    }
}
