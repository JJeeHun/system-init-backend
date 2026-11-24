package com.example.systeminitbackend.domain.mapping

import com.example.systeminitbackend.domain.Account
import com.example.systeminitbackend.domain.Menu
import com.example.systeminitbackend.domain.base.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["account_id", "menu_id"])])
class AccountMenu(
    account: Account,
    menu: Menu,
    canView: Boolean,
    canCreate: Boolean,
    canUpdate: Boolean,
    canDelete: Boolean,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false, updatable = false)
    var account: Account = account
        protected set

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_id", nullable = false, updatable = false)
    var menu: Menu = menu
        protected set

    @Column(nullable = false)
    var canView: Boolean = canView
        protected set

    @Column(nullable = false)
    var canCreate: Boolean = canCreate
        protected set

    @Column(nullable = false)
    var canUpdate: Boolean = canUpdate
        protected set

    @Column(nullable = false)
    var canDelete: Boolean = canDelete
        protected set

    fun changePermissions(canView: Boolean, canCreate: Boolean, canUpdate: Boolean, canDelete: Boolean) {
        this.canView = canView
        this.canCreate = canCreate
        this.canUpdate = canUpdate
        this.canDelete = canDelete
    }
}
