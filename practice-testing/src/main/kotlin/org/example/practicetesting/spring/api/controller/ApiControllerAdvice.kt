package org.example.practicetesting.spring.api.controller

import org.example.practicetesting.spring.api.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException::class)
    fun bindException(e: BindException): ApiResponse<Any> {
//        val bind
        return ApiResponse.of(
            status = HttpStatus.BAD_REQUEST,
            message = HttpStatus.BAD_REQUEST.name,
            data = e.bindingResult.allErrors[0].defaultMessage!!,
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableException(e: HttpMessageNotReadableException): ApiResponse<Any> =
        ApiResponse.of(
            status = HttpStatus.BAD_REQUEST,
            message = HttpStatus.BAD_REQUEST.name,
            data = e.message ?: "잘못된 요청입니다.",
        )
}
