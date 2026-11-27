package com.example.systeminitbackend.domain.layer1.accountrole

import com.example.systeminitbackend.domain.common.BaseEntity
import com.example.systeminitbackend.domain.layer0.account.Account
import com.example.systeminitbackend.domain.layer0.role.Role
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
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["account_id", "role_id"])])
class AccountRole(
    account: Account,
    role: Role,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false, updatable = false)
    var account: Account = account
        protected set

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false, updatable = false)
    var role: Role = role
        protected set
}
