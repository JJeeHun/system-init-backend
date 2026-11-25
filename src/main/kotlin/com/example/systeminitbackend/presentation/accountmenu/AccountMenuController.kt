package com.example.systeminitbackend.presentation.accountmenu

import com.example.systeminitbackend.application.accountmenu.AccountMenuFacade
import com.example.systeminitbackend.application.accountmenu.AccountMenuResult
import com.example.systeminitbackend.application.common.CountResult
import com.example.systeminitbackend.application.common.SliceResult
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

@Tag(name = "AccountMenu", description = "계정별 메뉴 권한")
@RestController
@RequestMapping("/api/account-menus")
class AccountMenuController(
    private val accountMenuFacade: AccountMenuFacade,
) {

    @Operation(summary = "계정 메뉴 권한 생성")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: CreateAccountMenuRequest): AccountMenuResult =
        accountMenuFacade.create(request.toCommand())

    @Operation(summary = "계정 메뉴 권한 단건 조회")
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): AccountMenuResult = accountMenuFacade.get(id)

    @Operation(summary = "계정 메뉴 권한 목록 조회")
    @GetMapping
    fun getAll(
        @ParameterObject @PageableDefault(size = 20, sort = ["id"], direction = Sort.Direction.DESC)
        pageable: Pageable,
    ): SliceResult<AccountMenuResult> = accountMenuFacade.getAll(pageable.withIdTiebreaker())

    @Operation(summary = "계정 메뉴 권한 전체 건수 조회")
    @GetMapping("/count")
    fun count(): CountResult = accountMenuFacade.count()

    @Operation(summary = "계정 메뉴 권한 변경")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody request: UpdateAccountMenuRequest): AccountMenuResult =
        accountMenuFacade.update(id, request.toCommand())

    @Operation(summary = "계정 메뉴 권한 삭제")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        accountMenuFacade.delete(id)
    }
}
