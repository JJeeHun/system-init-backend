package com.example.systeminitbackend.domain.layer0.commoncode

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Service

@Service
class CommonCodeService(
    private val commonCodeGroupRepository: CommonCodeGroupRepository,
    private val commonCodeRepository: CommonCodeRepository,
) {

    fun createGroup(
        code: String,
        name: String,
        description: String?,
        sortOrder: Int,
        enabled: Boolean,
    ): CommonCodeGroup {
        val group = CommonCodeGroup(
            code = code,
            name = name,
            description = description,
            sortOrder = sortOrder,
            enabled = enabled,
        )
        return commonCodeGroupRepository.save(group)
    }

    fun getGroupById(id: Long): CommonCodeGroup =
        commonCodeGroupRepository.findByIdAndDeletedFalse(id)
            ?: throw NoSuchElementException("공통코드 그룹을 찾을 수 없습니다. id=$id")

    fun getAllGroups(pageable: Pageable): Slice<CommonCodeGroup> = commonCodeGroupRepository.findAllByDeletedFalse(pageable)

    fun countGroups(): Long = commonCodeGroupRepository.countByDeletedFalse()

    fun updateGroup(id: Long, name: String, description: String?, sortOrder: Int, enabled: Boolean): CommonCodeGroup {
        val group = getGroupById(id)
        group.update(name, description, sortOrder, enabled)
        return group
    }

    fun deleteGroup(id: Long) {
        getGroupById(id).delete()
    }

    fun createCode(
        groupId: Long,
        code: String,
        name: String,
        description: String?,
        sortOrder: Int,
        enabled: Boolean,
    ): CommonCode {
        val commonCode = CommonCode(
            group = getGroupById(groupId),
            code = code,
            name = name,
            description = description,
            sortOrder = sortOrder,
            enabled = enabled,
        )
        return commonCodeRepository.save(commonCode)
    }

    fun getCodeById(id: Long): CommonCode =
        commonCodeRepository.findByIdAndDeletedFalse(id)
            ?: throw NoSuchElementException("공통코드를 찾을 수 없습니다. id=$id")

    fun getAllCodes(pageable: Pageable): Slice<CommonCode> = commonCodeRepository.findAllByDeletedFalse(pageable)

    fun countCodes(): Long = commonCodeRepository.countByDeletedFalse()

    fun updateCode(id: Long, name: String, description: String?, sortOrder: Int, enabled: Boolean): CommonCode {
        val commonCode = getCodeById(id)
        commonCode.update(name, description, sortOrder, enabled)
        return commonCode
    }

    fun deleteCode(id: Long) {
        getCodeById(id).delete()
    }
}
