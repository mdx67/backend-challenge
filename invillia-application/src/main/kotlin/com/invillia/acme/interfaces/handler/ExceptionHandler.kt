package com.invillia.acme.interfaces.handler

import com.invillia.acme.infra.InvilliaException
import com.invillia.acme.infra.error.ErrorCode
import com.invillia.acme.infra.error.InvilliaErrorCode
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@ControllerAdvice
class ExceptionHandler(
    private val messageSource: MessageSource
) : WebMvcConfigurerAdapter() {

    private val LOG = LoggerFactory.getLogger(this.javaClass)

//    @ExceptionHandler(InvilliaException.NotFoundException::class)
//    @SuppressWarnings
//    fun handlerNotFoundException(e: InvilliaException.NotFoundException): ResponseEntity<*> {
//        LOG.warn("ID: ${e.id} not found.")
//
//        val response = linkedMapOf(
//            "code" to InvilliaErrorCode.UNKNOWN_ERROR.code,
//            "message" to resolveErrorCode(e.errorCode!!)
//        )
//
//        return ResponseEntity.status(e.status).body(response)
//    }

    @ExceptionHandler(InvilliaException::class)
    @ResponseBody
    @SuppressWarnings
    fun handlerBusinessException(e: InvilliaException): ResponseEntity<*> {
        LOG.warn("Business or validation error: ${e.errorMessage}")

        val response = linkedMapOf(
            "code" to InvilliaErrorCode.UNKNOWN_ERROR.code,
            "message" to resolveErrorCode(e.errorCode!!)
        )

        return ResponseEntity.status(e.status).body(response)
    }

    @ResponseBody
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<*> {
        LOG.error("Internal error.", e)

        val response = linkedMapOf(
            "code" to InvilliaErrorCode.UNKNOWN_ERROR.code,
            "message" to resolveErrorCode(InvilliaErrorCode.UNKNOWN_ERROR),
            "exception" to e.message
        )

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response)
    }

    private fun resolveErrorCode(it: ErrorCode) =
        messageSource.getMessage(it.key, it.parameters, LocaleContextHolder.getLocale())
}
