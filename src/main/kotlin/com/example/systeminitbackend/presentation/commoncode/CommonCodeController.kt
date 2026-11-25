package com.example.systeminitbackend.presentation.commoncode

import com.example.systeminitbackend.application.common.CountResult
import com.example.systeminitbackend.application.common.SliceResult
import com.example.systeminitbackend.application.commoncode.CommonCodeFacade
import com.example.systeminitbackend.application.commoncode.CommonCodeResult
import com.example.systeminitbackend.presentation.common.withIdTiebreaker
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "CommonCode", description = "공통코드")
@RestController
@RequestMapping("/api/common-codes")
class CommonCodeController(
    private val commonCodeFacade: CommonCodeFacade,
) {

    @Operation(summary = "공통코드 생성")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: CreateCommonCodeRequest): CommonCodeResult =
        commonCodeFacade.createCode(request.toCommand())

    @Operation(summary = "공통코드 단건 조회")
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): CommonCodeResult = commonCodeFacade.getCode(id)

    @Operation(summary = "공통코드 목록 조회")
    @GetMapping
    fun getAll(
        @ParameterObject @PageableDefault(size = 20, sort = ["id"], direction = Sort.Direction.DESC)
        pageable: Pageable,
    ): SliceResult<CommonCodeResult> = commonCodeFacade.getAllCodes(pageable.withIdTiebreaker())

    @Operation(summary = "공통코드 전체 건수 조회")
    @GetMapping("/count")
    fun count(): CountResult = commonCodeFacade.countCodes()

    @Operation(summary = "공통코드 수정")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody request: UpdateCommonCodeRequest): CommonCodeResult =
        commonCodeFacade.updateCode(id, request.toCommand())

    @Operation(summary = "공통코드 삭제")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        commonCodeFacade.deleteCode(id)
    }
}
