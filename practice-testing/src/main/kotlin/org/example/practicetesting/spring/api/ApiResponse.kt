package org.example.practicetesting.spring.api

import org.springframework.http.HttpStatus

data class ApiResponse<T>(
    val status: HttpStatus,
    val code: Int,
    val message: String,
    val data: T,
) {
    companion object {
        fun <T> of(
            status: HttpStatus,
            message: String,
            data: T,
        ): ApiResponse<T> =
            ApiResponse(
                status = status,
                code = status.value(),
                message = message,
                data = data,
            )

        fun <T> of(
            status: HttpStatus,
            data: T,
        ): ApiResponse<T> = of(status, status.name, data)

        fun <T> ok(data: T): ApiResponse<T> = of(HttpStatus.OK, data)
    }
}
