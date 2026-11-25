package com.example.systeminitbackend.application.notification

import com.example.systeminitbackend.domain.layer1.notification.Notification
import java.time.LocalDateTime

data class NotificationResult(
    val id: Long,
    val recipientId: Long,
    val title: String,
    val content: String,
    val linkPath: String?,
    val isRead: Boolean,
    val readAt: LocalDateTime?,
) {
    companion object {
        fun from(notification: Notification): NotificationResult = NotificationResult(
            id = notification.id!!,
            recipientId = notification.recipient.id!!,
            title = notification.title,
            content = notification.content,
            linkPath = notification.linkPath,
            isRead = notification.isRead,
            readAt = notification.readAt,
        )
    }
}
