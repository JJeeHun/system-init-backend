package com.example.systeminitbackend.application.commoncode

import com.example.systeminitbackend.domain.CommonCode
import com.example.systeminitbackend.domain.CommonCodeGroup
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CommonCodeService(
    private val commonCodeGroupRepository: CommonCodeGroupRepository,
    private val commonCodeRepository: CommonCodeRepository,
) {

    @Transactional
    fun createGroup(command: CreateCommonCodeGroupCommand): CommonCodeGroupResult {
        val group = CommonCodeGroup(
            code = command.code,
            name = command.name,
            description = command.description,
            sortOrder = command.sortOrder,
            enabled = command.enabled,
        )
        return CommonCodeGroupResult.from(commonCodeGroupRepository.save(group))
    }

    fun getGroup(id: Long): CommonCodeGroupResult = CommonCodeGroupResult.from(findGroup(id))

    fun getAllGroups(): List<CommonCodeGroupResult> =
        commonCodeGroupRepository.findAllByDeletedFalse().map(CommonCodeGroupResult::from)

    @Transactional
    fun updateGroup(id: Long, command: UpdateCommonCodeGroupCommand): CommonCodeGroupResult {
        val group = findGroup(id)
        group.update(command.name, command.description, command.sortOrder, command.enabled)
        return CommonCodeGroupResult.from(group)
    }

    @Transactional
    fun deleteGroup(id: Long) {
        findGroup(id).delete()
    }

    @Transactional
    fun createCode(command: CreateCommonCodeCommand): CommonCodeResult {
        val commonCode = CommonCode(
            group = findGroup(command.groupId),
            code = command.code,
            name = command.name,
            description = command.description,
            sortOrder = command.sortOrder,
            enabled = command.enabled,
        )
        return CommonCodeResult.from(commonCodeRepository.save(commonCode))
    }

    fun getCode(id: Long): CommonCodeResult = CommonCodeResult.from(findCode(id))

    fun getAllCodes(): List<CommonCodeResult> =
        commonCodeRepository.findAllByDeletedFalse().map(CommonCodeResult::from)

    @Transactional
    fun updateCode(id: Long, command: UpdateCommonCodeCommand): CommonCodeResult {
        val commonCode = findCode(id)
        commonCode.update(command.name, command.description, command.sortOrder, command.enabled)
        return CommonCodeResult.from(commonCode)
    }

    @Transactional
    fun deleteCode(id: Long) {
        findCode(id).delete()
    }

    private fun findGroup(id: Long): CommonCodeGroup =
        commonCodeGroupRepository.findByIdAndDeletedFalse(id)
            ?: throw NoSuchElementException("공통코드 그룹을 찾을 수 없습니다. id=$id")

    private fun findCode(id: Long): CommonCode =
        commonCodeRepository.findByIdAndDeletedFalse(id)
            ?: throw NoSuchElementException("공통코드를 찾을 수 없습니다. id=$id")
}
