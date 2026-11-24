package com.example.systeminitbackend.application.commoncode

import com.example.systeminitbackend.domain.CommonCode
import org.springframework.data.jpa.repository.JpaRepository

interface CommonCodeRepository : JpaRepository<CommonCode, Long> {
    fun findByIdAndDeletedFalse(id: Long): CommonCode?

    fun findAllByDeletedFalse(): List<CommonCode>
}
