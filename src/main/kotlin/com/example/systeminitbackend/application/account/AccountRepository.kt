package com.example.systeminitbackend.application.account

import com.example.systeminitbackend.domain.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {
    fun findByIdAndDeletedFalse(id: Long): Account?

    fun findAllByDeletedFalse(): List<Account>
}
