package com.example.systeminitbackend.domain.layer0.commoncode

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository

interface CommonCodeRepository : JpaRepository<CommonCode, Long> {
    fun findByIdAndDeletedFalse(id: Long): CommonCode?

    fun findAllByDeletedFalse(pageable: Pageable): Slice<CommonCode>

    fun countByDeletedFalse(): Long
}
