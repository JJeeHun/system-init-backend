package com.example.systeminitbackend.presentation.common

import org.springframework.data.core.PropertyReferenceException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(e: NoSuchElementException): ErrorResponse =
        ErrorResponse(code = "NOT_FOUND", message = e.message ?: "대상을 찾을 수 없습니다.")

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidation(e: MethodArgumentNotValidException): ErrorResponse =
        ErrorResponse(
            code = "INVALID_INPUT",
            message = "입력값이 올바르지 않습니다.",
            fieldErrors = e.bindingResult.fieldErrors.map {
                ErrorResponse.FieldError(field = it.field, message = it.defaultMessage ?: "올바르지 않은 값입니다.")
            },
        )

    @ExceptionHandler(PropertyReferenceException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleInvalidSort(e: PropertyReferenceException): ErrorResponse =
        ErrorResponse(code = "INVALID_SORT", message = "정렬할 수 없는 필드입니다. field=${e.propertyName}")

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleNotReadable(e: HttpMessageNotReadableException): ErrorResponse =
        ErrorResponse(code = "INVALID_BODY", message = "요청 본문을 읽을 수 없습니다. 필수 값과 형식을 확인하세요.")
}
