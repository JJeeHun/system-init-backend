package com.example.systeminitbackend.application.account

data class CreateAccountCommand(
    val loginId: String,
    val rawPassword: String,
    val name: String,
    val email: String,
    val centerCode: String,
    val enabled: Boolean,
)

data class UpdateAccountCommand(
    val name: String,
    val email: String,
    val centerCode: String,
)
