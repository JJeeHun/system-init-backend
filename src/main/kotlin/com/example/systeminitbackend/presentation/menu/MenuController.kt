package com.example.systeminitbackend.presentation.menu

import com.example.systeminitbackend.application.common.CountResult
import com.example.systeminitbackend.application.common.SliceResult
import com.example.systeminitbackend.application.menu.MenuFacade
import com.example.systeminitbackend.application.menu.MenuResult
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

@Tag(name = "Menu", description = "메뉴")
@RestController
@RequestMapping("/api/menus")
class MenuController(
    private val menuFacade: MenuFacade,
) {

    @Operation(summary = "메뉴 생성")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: CreateMenuRequest): MenuResult = menuFacade.create(request.toCommand())

    @Operation(summary = "메뉴 단건 조회")
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): MenuResult = menuFacade.get(id)

    @Operation(summary = "메뉴 목록 조회")
    @GetMapping
    fun getAll(
        @ParameterObject @PageableDefault(size = 20, sort = ["id"], direction = Sort.Direction.DESC)
        pageable: Pageable,
    ): SliceResult<MenuResult> = menuFacade.getAll(pageable.withIdTiebreaker())

    @Operation(summary = "메뉴 전체 건수 조회")
    @GetMapping("/count")
    fun count(): CountResult = menuFacade.count()

    @Operation(summary = "메뉴 수정")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody request: UpdateMenuRequest): MenuResult =
        menuFacade.update(id, request.toCommand())

    @Operation(summary = "메뉴 삭제")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        menuFacade.delete(id)
    }
}
