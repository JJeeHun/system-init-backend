package com.example.systeminitbackend.presentation.notification

import com.example.systeminitbackend.application.common.CountResult
import com.example.systeminitbackend.application.common.SliceResult
import com.example.systeminitbackend.application.notification.NotificationFacade
import com.example.systeminitbackend.application.notification.NotificationResult
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
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Notification", description = "알림")
@RestController
@RequestMapping("/api/notifications")
class NotificationController(
    private val notificationFacade: NotificationFacade,
) {

    @Operation(summary = "알림 생성")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: CreateNotificationRequest): NotificationResult =
        notificationFacade.create(request.toCommand())

    @Operation(summary = "알림 단건 조회")
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): NotificationResult = notificationFacade.get(id)

    @Operation(summary = "알림 목록 조회")
    @GetMapping
    fun getAll(
        @ParameterObject @PageableDefault(size = 20, sort = ["id"], direction = Sort.Direction.DESC)
        pageable: Pageable,
    ): SliceResult<NotificationResult> = notificationFacade.getAll(pageable.withIdTiebreaker())

    @Operation(summary = "알림 전체 건수 조회")
    @GetMapping("/count")
    fun count(): CountResult = notificationFacade.count()

    @Operation(summary = "알림 읽음 처리")
    @PatchMapping("/{id}/read")
    fun markAsRead(@PathVariable id: Long): NotificationResult = notificationFacade.markAsRead(id)

    @Operation(summary = "알림 삭제")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        notificationFacade.delete(id)
    }
}
