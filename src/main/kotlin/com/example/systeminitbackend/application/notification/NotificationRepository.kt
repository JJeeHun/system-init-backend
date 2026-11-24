package com.example.systeminitbackend.application.notification

import com.example.systeminitbackend.domain.Notification
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepository : JpaRepository<Notification, Long> {
    fun findByIdAndDeletedFalse(id: Long): Notification?

    fun findAllByDeletedFalse(): List<Notification>
}
