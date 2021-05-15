package com.example.lesson9.api

import com.example.lesson9.exceptions.StudentNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ErrorController : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [(StudentNotFoundException::class)])
    fun handleUserAlreadyExists(ex: StudentNotFoundException,request: WebRequest): ResponseEntity<String> {
        val message = ex.message ?: "Not Found"
        return ResponseEntity(message, HttpStatus.NOT_FOUND)
    }
}