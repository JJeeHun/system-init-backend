package com.example.systeminitbackend.application.commoncode

import com.example.systeminitbackend.application.common.CountResult
import com.example.systeminitbackend.application.common.SliceResult
import com.example.systeminitbackend.domain.layer0.commoncode.CommonCodeService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CommonCodeFacade(
    private val commonCodeService: CommonCodeService,
) {

    @Transactional
    fun createGroup(command: CreateCommonCodeGroupCommand): CommonCodeGroupResult {
        val group = commonCodeService.createGroup(
            code = command.code,
            name = command.name,
            description = command.description,
            sortOrder = command.sortOrder,
            enabled = command.enabled,
        )
        return CommonCodeGroupResult.from(group)
    }

    fun getGroup(id: Long): CommonCodeGroupResult = CommonCodeGroupResult.from(commonCodeService.getGroupById(id))

    fun getAllGroups(pageable: Pageable): SliceResult<CommonCodeGroupResult> =
        SliceResult.from(commonCodeService.getAllGroups(pageable), CommonCodeGroupResult::from)

    fun countGroups(): CountResult = CountResult(commonCodeService.countGroups())

    @Transactional
    fun updateGroup(id: Long, command: UpdateCommonCodeGroupCommand): CommonCodeGroupResult {
        val group = commonCodeService.updateGroup(id, command.name, command.description, command.sortOrder, command.enabled)
        return CommonCodeGroupResult.from(group)
    }

    @Transactional
    fun deleteGroup(id: Long) {
        commonCodeService.deleteGroup(id)
    }

    @Transactional
    fun createCode(command: CreateCommonCodeCommand): CommonCodeResult {
        val commonCode = commonCodeService.createCode(
            groupId = command.groupId,
            code = command.code,
            name = command.name,
            description = command.description,
            sortOrder = command.sortOrder,
            enabled = command.enabled,
        )
        return CommonCodeResult.from(commonCode)
    }

    fun getCode(id: Long): CommonCodeResult = CommonCodeResult.from(commonCodeService.getCodeById(id))

    fun getAllCodes(pageable: Pageable): SliceResult<CommonCodeResult> =
        SliceResult.from(commonCodeService.getAllCodes(pageable), CommonCodeResult::from)

    fun countCodes(): CountResult = CountResult(commonCodeService.countCodes())

    @Transactional
    fun updateCode(id: Long, command: UpdateCommonCodeCommand): CommonCodeResult {
        val commonCode = commonCodeService.updateCode(id, command.name, command.description, command.sortOrder, command.enabled)
        return CommonCodeResult.from(commonCode)
    }

    @Transactional
    fun deleteCode(id: Long) {
        commonCodeService.deleteCode(id)
    }
}
