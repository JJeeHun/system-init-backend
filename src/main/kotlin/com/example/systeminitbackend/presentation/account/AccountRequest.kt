package com.example.systeminitbackend.presentation.account

import com.example.systeminitbackend.application.account.CreateAccountCommand
import com.example.systeminitbackend.application.account.UpdateAccountCommand
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateAccountRequest(
    @field:NotBlank @field:Size(max = 50)
    val loginId: String,
    @field:NotBlank @field:Size(min = 8, max = 100)
    val password: String,
    @field:NotBlank @field:Size(max = 50)
    val name: String,
    @field:NotBlank @field:Email @field:Size(max = 100)
    val email: String,
    @field:NotBlank @field:Size(max = 50)
    val centerCode: String,
    val enabled: Boolean = true,
) {
    fun toCommand() = CreateAccountCommand(
        loginId = loginId,
        rawPassword = password,
        name = name,
        email = email,
        centerCode = centerCode,
        enabled = enabled,
    )
}

data class UpdateAccountRequest(
    @field:NotBlank @field:Size(max = 50)
    val name: String,
    @field:NotBlank @field:Email @field:Size(max = 100)
    val email: String,
    @field:NotBlank @field:Size(max = 50)
    val centerCode: String,
) {
    fun toCommand() = UpdateAccountCommand(name = name, email = email, centerCode = centerCode)
}
