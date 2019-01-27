package com.invillia.acme.infra

sealed class InvilliaException(
    val status: Int,
    open val errorMessage: String? = null,
    open val id: String? = null
) : RuntimeException() {

    companion object {
        const val VALIDATION_STATUS = 400
        const val BUSINESS_STATUS = 422
        const val NOT_FOUND_STATUS = 404
    }

    class BusinessException(override val errorMessage: String? = null) :
        InvilliaException(BUSINESS_STATUS, errorMessage)

    class ValidationException(override val errorMessage: String? = null) :
        InvilliaException(VALIDATION_STATUS, errorMessage)

    class NotFoundException(override val id: String) :
        InvilliaException(NOT_FOUND_STATUS, id)

}
