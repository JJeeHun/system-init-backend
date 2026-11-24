package com.example.systeminitbackend.application.accountmenu

import com.example.systeminitbackend.domain.mapping.AccountMenu
import org.springframework.data.jpa.repository.JpaRepository

interface AccountMenuRepository : JpaRepository<AccountMenu, Long> {
    fun findByIdAndDeletedFalse(id: Long): AccountMenu?

    fun findAllByDeletedFalse(): List<AccountMenu>
}
