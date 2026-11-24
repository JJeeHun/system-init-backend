package com.example.systeminitbackend.domain

import com.example.systeminitbackend.domain.base.BaseEntity
import com.example.systeminitbackend.domain.enums.MenuScope
import com.example.systeminitbackend.domain.enums.MenuType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Menu(
    parent: Menu?,
    code: String,
    name: String,
    labelKey: String?,
    type: MenuType,
    path: String?,
    icon: String?,
    sortOrder: Int,
    scope: MenuScope,
    enabled: Boolean,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: Menu? = parent
        protected set

    @Column(nullable = false, unique = true, length = 50, updatable = false)
    var code: String = code
        protected set

    @Column(nullable = false, length = 50)
    var name: String = name
        protected set

    @Column(length = 100)
    var labelKey: String? = labelKey
        protected set

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var type: MenuType = type
        protected set

    @Column(length = 200)
    var path: String? = path
        protected set

    @Column(length = 50)
    var icon: String? = icon
        protected set

    @Column(nullable = false)
    var sortOrder: Int = sortOrder
        protected set

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var scope: MenuScope = scope
        protected set

    @Column(nullable = false)
    var enabled: Boolean = enabled
        protected set

    fun update(
        parent: Menu?,
        name: String,
        labelKey: String?,
        type: MenuType,
        path: String?,
        icon: String?,
        sortOrder: Int,
        scope: MenuScope,
        enabled: Boolean,
    ) {
        this.parent = parent
        this.name = name
        this.labelKey = labelKey
        this.type = type
        this.path = path
        this.icon = icon
        this.sortOrder = sortOrder
        this.scope = scope
        this.enabled = enabled
    }
}
