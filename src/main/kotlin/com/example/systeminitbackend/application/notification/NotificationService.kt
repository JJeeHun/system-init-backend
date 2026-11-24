package com.example.systeminitbackend.application.notification

import com.example.systeminitbackend.application.account.AccountRepository
import com.example.systeminitbackend.domain.Notification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class NotificationService(
    private val notificationRepository: NotificationRepository,
    private val accountRepository: AccountRepository,
) {

    @Transactional
    fun create(command: CreateNotificationCommand): NotificationResult {
        val recipient = accountRepository.findByIdAndDeletedFalse(command.recipientId)
            ?: throw NoSuchElementException("계정을 찾을 수 없습니다. id=${command.recipientId}")
        val notification = Notification(
            recipient = recipient,
            title = command.title,
            content = command.content,
            linkPath = command.linkPath,
        )
        return NotificationResult.from(notificationRepository.save(notification))
    }

    fun get(id: Long): NotificationResult = NotificationResult.from(findNotification(id))

    fun getAll(): List<NotificationResult> =
        notificationRepository.findAllByDeletedFalse().map(NotificationResult::from)

    @Transactional
    fun markAsRead(id: Long): NotificationResult {
        val notification = findNotification(id)
        notification.markAsRead()
        return NotificationResult.from(notification)
    }

    @Transactional
    fun delete(id: Long) {
        findNotification(id).delete()
    }

    private fun findNotification(id: Long): Notification =
        notificationRepository.findByIdAndDeletedFalse(id)
            ?: throw NoSuchElementException("알림을 찾을 수 없습니다. id=$id")
}
