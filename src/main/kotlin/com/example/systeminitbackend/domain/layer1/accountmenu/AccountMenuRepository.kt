package com.example.systeminitbackend.domain.layer1.accountmenu

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository

interface AccountMenuRepository : JpaRepository<AccountMenu, Long> {
    fun findByIdAndDeletedFalse(id: Long): AccountMenu?

    fun findAllByDeletedFalse(pageable: Pageable): Slice<AccountMenu>

    fun countByDeletedFalse(): Long
}
