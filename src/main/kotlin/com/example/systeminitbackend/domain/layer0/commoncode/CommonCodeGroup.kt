package com.example.systeminitbackend.domain.layer0.commoncode

import com.example.systeminitbackend.domain.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class CommonCodeGroup(
    code: String,
    name: String,
    description: String?,
    sortOrder: Int,
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
    var sortOrder: Int = sortOrder
        protected set

    @Column(nullable = false)
    var enabled: Boolean = enabled
        protected set

    init {
        require(code.isNotBlank()) { "그룹 코드는 필수입니다." }
        validate(name, sortOrder)
    }

    fun update(name: String, description: String?, sortOrder: Int, enabled: Boolean) {
        validate(name, sortOrder)
        this.name = name
        this.description = description
        this.sortOrder = sortOrder
        this.enabled = enabled
    }

    private fun validate(name: String, sortOrder: Int) {
        require(name.isNotBlank()) { "그룹 이름은 필수입니다." }
        require(sortOrder >= 0) { "정렬 순서는 0 이상이어야 합니다." }
    }
}
