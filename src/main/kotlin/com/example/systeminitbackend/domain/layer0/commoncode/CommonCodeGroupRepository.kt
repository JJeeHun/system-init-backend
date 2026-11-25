package com.example.systeminitbackend.domain.layer0.commoncode

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository

interface CommonCodeGroupRepository : JpaRepository<CommonCodeGroup, Long> {
    fun findByIdAndDeletedFalse(id: Long): CommonCodeGroup?

    fun findAllByDeletedFalse(pageable: Pageable): Slice<CommonCodeGroup>

    fun countByDeletedFalse(): Long
}
