package com.example.systeminitbackend.application.commoncode

import com.example.systeminitbackend.domain.CommonCodeGroup
import org.springframework.data.jpa.repository.JpaRepository

interface CommonCodeGroupRepository : JpaRepository<CommonCodeGroup, Long> {
    fun findByIdAndDeletedFalse(id: Long): CommonCodeGroup?

    fun findAllByDeletedFalse(): List<CommonCodeGroup>
}
