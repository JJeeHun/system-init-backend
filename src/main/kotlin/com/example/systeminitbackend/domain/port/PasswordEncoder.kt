package com.example.systeminitbackend.domain.port

interface PasswordEncoder {
    fun encode(rawPassword: String): String
}
