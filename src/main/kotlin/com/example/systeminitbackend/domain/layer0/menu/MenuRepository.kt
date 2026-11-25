package com.example.systeminitbackend.domain.layer0.menu

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository : JpaRepository<Menu, Long> {
    fun findByIdAndDeletedFalse(id: Long): Menu?

    fun findAllByDeletedFalse(pageable: Pageable): Slice<Menu>

    fun countByDeletedFalse(): Long
}
