package com.example.systeminitbackend.application.account

import com.example.systeminitbackend.domain.Account
import com.example.systeminitbackend.domain.port.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccountService(
    private val accountRepository: AccountRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    @Transactional
    fun create(command: CreateAccountCommand): AccountResult {
        val account = Account.register(
            loginId = command.loginId,
            rawPassword = command.rawPassword,
            name = command.name,
            email = command.email,
            centerCode = command.centerCode,
            enabled = command.enabled,
            passwordEncoder = passwordEncoder,
        )
        return AccountResult.from(accountRepository.save(account))
    }

    fun get(id: Long): AccountResult = AccountResult.from(findAccount(id))

    fun getAll(): List<AccountResult> = accountRepository.findAllByDeletedFalse().map(AccountResult::from)

    @Transactional
    fun update(id: Long, command: UpdateAccountCommand): AccountResult {
        val account = findAccount(id)
        account.updateProfile(command.name, command.email, command.centerCode)
        return AccountResult.from(account)
    }

    @Transactional
    fun delete(id: Long) {
        findAccount(id).delete()
    }

    private fun findAccount(id: Long): Account =
        accountRepository.findByIdAndDeletedFalse(id) ?: throw NoSuchElementException("계정을 찾을 수 없습니다. id=$id")
}
