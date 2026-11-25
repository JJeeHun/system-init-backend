package com.example.systeminitbackend.domain.layer0.account

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {
    fun findByIdAndDeletedFalse(id: Long): Account?

    fun findAllByDeletedFalse(pageable: Pageable): Slice<Account>

    fun countByDeletedFalse(): Long
}
