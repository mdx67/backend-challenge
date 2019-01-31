package com.invillia.acme.infra.error

class ErrorCode(
    val code: String? = null,
    val key: String? = null,
    val parameters: Array<String>? = null
)

object InvilliaErrorCode {

    val UNKNOWN_ERROR: ErrorCode = ErrorCode("INVILLIA-001", "unknown.error")
    val BUSINESS_ERROR: ErrorCode = ErrorCode("INVILLIA-002", "business.error")
    val VALIDATION_ERROR: ErrorCode = ErrorCode("INVILLIA-003", "business.error")
    val INTEGRATION_ERROR: ErrorCode = ErrorCode("INVILLIA-004", "integration.error")
    val NOT_FOUND_ERROR: ErrorCode = ErrorCode("INVILLIA-005", "not.found.error")
    val NOT_REFUND_ALLOWED: ErrorCode = ErrorCode("INVILLIA-006", "not.refund.allowed")
    val ORDER_NOT_INFORMED: ErrorCode = ErrorCode("INVILLIA-007", "order.not.informed")

}
