package com.example.systeminitbackend.domain.layer1.notification

import com.example.systeminitbackend.domain.layer0.account.Account
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val notificationRepository: NotificationRepository,
) {

    fun create(recipient: Account, title: String, content: String, linkPath: String?): Notification {
        val notification = Notification(
            recipient = recipient,
            title = title,
            content = content,
            linkPath = linkPath,
        )
        return notificationRepository.save(notification)
    }

    fun getById(id: Long): Notification =
        notificationRepository.findByIdAndDeletedFalse(id)
            ?: throw NoSuchElementException("알림을 찾을 수 없습니다. id=$id")

    fun getAll(pageable: Pageable): Slice<Notification> = notificationRepository.findAllByDeletedFalse(pageable)

    fun count(): Long = notificationRepository.countByDeletedFalse()

    fun markAsRead(id: Long): Notification {
        val notification = getById(id)
        notification.markAsRead()
        return notification
    }

    fun delete(id: Long) {
        getById(id).delete()
    }
}
