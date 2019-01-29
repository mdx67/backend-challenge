package com.invillia.acme.infra

import com.invillia.acme.infra.error.ErrorCode

sealed class InvilliaException(
    val status: Int,
    open val errorCode: ErrorCode? = null,
    open val errorMessage: String? = null,
    open val id: String? = null
) : RuntimeException() {

    companion object {
        const val VALIDATION_STATUS = 400
        const val BUSINESS_STATUS = 422
        const val NOT_FOUND_STATUS = 404
    }

    class BusinessException(
        override val errorCode: ErrorCode? = null,
        override val errorMessage: String? = null
    ) : InvilliaException(BUSINESS_STATUS, errorCode, errorMessage)

    class ValidationException(
        override val errorCode: ErrorCode? = null,
        override val errorMessage: String? = null
    ) : InvilliaException(VALIDATION_STATUS, errorCode, errorMessage)

    class NotFoundException(
        override val errorCode: ErrorCode? = null,
        override val id: String
    ) : InvilliaException(NOT_FOUND_STATUS, errorCode, id)

}
