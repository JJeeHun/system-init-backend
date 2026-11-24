package com.example.systeminitbackend.application.notification

data class CreateNotificationCommand(
    val recipientId: Long,
    val title: String,
    val content: String,
    val linkPath: String?,
)
