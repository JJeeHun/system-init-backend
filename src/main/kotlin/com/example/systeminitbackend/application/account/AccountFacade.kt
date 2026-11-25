package com.example.systeminitbackend.application.account

import com.example.systeminitbackend.application.common.CountResult
import com.example.systeminitbackend.application.common.SliceResult
import com.example.systeminitbackend.domain.layer0.account.AccountService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccountFacade(
    private val accountService: AccountService,
) {

    @Transactional
    fun create(command: CreateAccountCommand): AccountResult {
        val account = accountService.register(
            loginId = command.loginId,
            rawPassword = command.rawPassword,
            name = command.name,
            email = command.email,
            centerCode = command.centerCode,
            enabled = command.enabled,
        )
        return AccountResult.from(account)
    }

    fun get(id: Long): AccountResult = AccountResult.from(accountService.getById(id))

    fun getAll(pageable: Pageable): SliceResult<AccountResult> =
        SliceResult.from(accountService.getAll(pageable), AccountResult::from)

    fun count(): CountResult = CountResult(accountService.count())

    @Transactional
    fun update(id: Long, command: UpdateAccountCommand): AccountResult =
        AccountResult.from(accountService.updateProfile(id, command.name, command.email, command.centerCode))

    @Transactional
    fun delete(id: Long) {
        accountService.delete(id)
    }
}
