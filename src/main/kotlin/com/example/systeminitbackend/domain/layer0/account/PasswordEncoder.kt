package com.example.systeminitbackend.domain.layer0.account

interface PasswordEncoder {
    fun encode(rawPassword: String): String

    fun matches(rawPassword: String, encodedPassword: String): Boolean
}
