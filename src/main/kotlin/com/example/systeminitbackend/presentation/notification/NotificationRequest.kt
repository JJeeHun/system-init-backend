package com.example.systeminitbackend.presentation.notification

import com.example.systeminitbackend.application.notification.CreateNotificationCommand
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateNotificationRequest(
    val recipientId: Long,
    @field:NotBlank @field:Size(max = 100)
    val title: String,
    @field:NotBlank @field:Size(max = 1000)
    val content: String,
    @field:Size(max = 200)
    val linkPath: String? = null,
) {
    fun toCommand() = CreateNotificationCommand(
        recipientId = recipientId,
        title = title,
        content = content,
        linkPath = linkPath,
    )
}
