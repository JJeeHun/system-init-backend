package com.example.systeminitbackend.domain.layer0.account

import com.example.systeminitbackend.domain.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Account private constructor(
    loginId: String,
    password: String,
    name: String,
    email: String,
    centerCode: String,
    enabled: Boolean,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false, unique = true, length = 50, updatable = false)
    var loginId: String = loginId
        protected set

    @Column(nullable = false, length = 255)
    var password: String = password
        protected set

    @Column(nullable = false, length = 50)
    var name: String = name
        protected set

    @Column(nullable = false, unique = true, length = 100)
    var email: String = email
        protected set

    @Column(nullable = false, length = 50)
    var centerCode: String = centerCode
        protected set

    @Column(nullable = false)
    var enabled: Boolean = enabled
        protected set

    companion object {
        fun register(
            loginId: String,
            rawPassword: String,
            name: String,
            email: String,
            centerCode: String,
            enabled: Boolean,
            passwordEncoder: PasswordEncoder,
        ): Account = Account(loginId, passwordEncoder.encode(rawPassword), name, email, centerCode, enabled)
    }

    fun updateProfile(name: String, email: String, centerCode: String) {
        this.name = name
        this.email = email
        this.centerCode = centerCode
    }

    fun changePassword(rawPassword: String, passwordEncoder: PasswordEncoder) {
        this.password = passwordEncoder.encode(rawPassword)
    }

    fun matchesPassword(rawPassword: String, passwordEncoder: PasswordEncoder): Boolean =
        passwordEncoder.matches(rawPassword, this.password)

    fun enable() {
        this.enabled = true
    }

    fun disable() {
        this.enabled = false
    }
}
