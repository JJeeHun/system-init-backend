package com.example.systeminitbackend.application.commoncode

data class CreateCommonCodeGroupCommand(
    val code: String,
    val name: String,
    val description: String?,
    val sortOrder: Int,
    val enabled: Boolean,
)

data class UpdateCommonCodeGroupCommand(
    val name: String,
    val description: String?,
    val sortOrder: Int,
    val enabled: Boolean,
)

data class CreateCommonCodeCommand(
    val groupId: Long,
    val code: String,
    val name: String,
    val description: String?,
    val sortOrder: Int,
    val enabled: Boolean,
)

data class UpdateCommonCodeCommand(
    val name: String,
    val description: String?,
    val sortOrder: Int,
    val enabled: Boolean,
)
