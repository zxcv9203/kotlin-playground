package org.example.tdd.model.command

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class InvalidCommandExceptionHandler {
    @ExceptionHandler(InvalidCommandException::class)
    fun handleInvalidCommandException(): ResponseEntity<Unit> = ResponseEntity.badRequest().build()
}
