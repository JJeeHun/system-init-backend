package com.example.systeminitbackend.domain

import com.example.systeminitbackend.domain.base.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
class Notification(
    recipient: Account,
    title: String,
    content: String,
    linkPath: String?,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recipient_id", nullable = false, updatable = false)
    var recipient: Account = recipient
        protected set

    @Column(nullable = false, length = 100)
    var title: String = title
        protected set

    @Column(nullable = false, length = 1000)
    var content: String = content
        protected set

    @Column(length = 200)
    var linkPath: String? = linkPath
        protected set

    @Column(nullable = false)
    var isRead: Boolean = false
        protected set

    var readAt: LocalDateTime? = null
        protected set

    fun markAsRead() {
        this.isRead = true
        this.readAt = LocalDateTime.now()
    }
}
