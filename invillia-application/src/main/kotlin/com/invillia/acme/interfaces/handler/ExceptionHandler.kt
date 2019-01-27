package com.invillia.acme.interfaces.handler

import com.invillia.acme.infra.InvilliaException
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@ControllerAdvice
class ExceptionHandler(
    //TODO: Implement with messageSource
    private val messageSource: MessageSource
) : WebMvcConfigurerAdapter() {

    private val LOG = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(InvilliaException.NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @SuppressWarnings
    fun handlerNotFoundException(e: InvilliaException.NotFoundException) {
        LOG.warn("ID: ${e.id} not found.")
    }

    @ExceptionHandler(InvilliaException::class)
    @ResponseBody
    @SuppressWarnings
    fun handlerBusinessException(e: InvilliaException): ResponseEntity<String> {
        LOG.warn("Business or validation error: ${e.errorMessage}")

        return ResponseEntity.status(e.status).body(e.errorMessage)
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): String {
        LOG.error("Internal error.", e)

        return "Internal error, please contact support."
    }
}
