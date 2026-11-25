package com.example.systeminitbackend.domain.layer0.account

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    fun register(
        loginId: String,
        rawPassword: String,
        name: String,
        email: String,
        centerCode: String?,
        enabled: Boolean,
    ): Account {
        val account = Account.register(
            loginId = loginId,
            rawPassword = rawPassword,
            name = name,
            email = email,
            centerCode = centerCode,
            enabled = enabled,
            passwordEncoder = passwordEncoder,
        )
        return accountRepository.save(account)
    }

    fun getById(id: Long): Account =
        accountRepository.findByIdAndDeletedFalse(id) ?: throw NoSuchElementException("계정을 찾을 수 없습니다. id=$id")

    fun getAll(pageable: Pageable): Slice<Account> = accountRepository.findAllByDeletedFalse(pageable)

    fun count(): Long = accountRepository.countByDeletedFalse()

    fun updateProfile(id: Long, name: String, email: String, centerCode: String?): Account {
        val account = getById(id)
        account.updateProfile(name, email, centerCode)
        return account
    }

    fun delete(id: Long) {
        getById(id).delete()
    }
}
