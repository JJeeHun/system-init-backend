package com.example.systeminitbackend.domain.layer1.notification

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepository : JpaRepository<Notification, Long> {
    fun findByIdAndDeletedFalse(id: Long): Notification?

    fun findAllByDeletedFalse(pageable: Pageable): Slice<Notification>

    fun countByDeletedFalse(): Long
}
