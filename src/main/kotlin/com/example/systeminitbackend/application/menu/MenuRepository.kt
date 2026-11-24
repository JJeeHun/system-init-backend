package com.example.systeminitbackend.application.menu

import com.example.systeminitbackend.domain.Menu
import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository : JpaRepository<Menu, Long> {
    fun findByIdAndDeletedFalse(id: Long): Menu?

    fun findAllByDeletedFalse(): List<Menu>
}
