package com.example.systeminitbackend.application.notification

import com.example.systeminitbackend.application.common.CountResult
import com.example.systeminitbackend.application.common.SliceResult
import com.example.systeminitbackend.domain.layer0.account.AccountService
import com.example.systeminitbackend.domain.layer1.notification.NotificationService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class NotificationFacade(
    private val notificationService: NotificationService,
    private val accountService: AccountService,
) {

    @Transactional
    fun create(command: CreateNotificationCommand): NotificationResult {
        val recipient = accountService.getById(command.recipientId)
        val notification = notificationService.create(
            recipient = recipient,
            title = command.title,
            content = command.content,
            linkPath = command.linkPath,
        )
        return NotificationResult.from(notification)
    }

    fun get(id: Long): NotificationResult = NotificationResult.from(notificationService.getById(id))

    fun getAll(pageable: Pageable): SliceResult<NotificationResult> =
        SliceResult.from(notificationService.getAll(pageable), NotificationResult::from)

    fun count(): CountResult = CountResult(notificationService.count())

    @Transactional
    fun markAsRead(id: Long): NotificationResult = NotificationResult.from(notificationService.markAsRead(id))

    @Transactional
    fun delete(id: Long) {
        notificationService.delete(id)
    }
}
