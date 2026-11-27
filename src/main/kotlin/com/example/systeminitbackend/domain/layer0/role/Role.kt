package com.example.systeminitbackend.domain.layer0.role

import com.example.systeminitbackend.domain.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Role(
    code: String,
    name: String,
    description: String?,
    enabled: Boolean,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false, unique = true, length = 50, updatable = false)
    var code: String = code
        protected set

    @Column(nullable = false, length = 50)
    var name: String = name
        protected set

    @Column(length = 255)
    var description: String? = description
        protected set

    @Column(nullable = false)
    var enabled: Boolean = enabled
        protected set

    init {
        require(code.isNotBlank()) { "역할 코드는 필수입니다." }
        validate(name)
    }

    fun update(name: String, description: String?, enabled: Boolean) {
        validate(name)
        this.name = name
        this.description = description
        this.enabled = enabled
    }

    private fun validate(name: String) {
        require(name.isNotBlank()) { "역할 이름은 필수입니다." }
    }
}
