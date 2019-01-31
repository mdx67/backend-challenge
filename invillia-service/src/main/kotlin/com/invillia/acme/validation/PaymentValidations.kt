package com.invillia.acme.validation

import com.invillia.acme.api.CreatePaymentData
import com.invillia.acme.domain.entity.OrderEntity
import com.invillia.acme.infra.InvilliaException
import com.invillia.acme.infra.error.InvilliaErrorCode
import org.springframework.stereotype.Component

@Component
class PaymentValidations {

    fun validPayment(payment: CreatePaymentData) {
        if (payment.orderId.isNullOrEmpty()) throw InvilliaException.ValidationException(InvilliaErrorCode.ORDER_NOT_INFORMED)
    }
}
