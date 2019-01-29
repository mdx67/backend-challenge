package com.invillia.acme.validation

import com.invillia.acme.domain.entity.OrderEntity
import com.invillia.acme.infra.InvilliaException
import com.invillia.acme.infra.error.InvilliaErrorCode
import org.springframework.stereotype.Component

@Component
class OrderValidations {

    fun validCanRefund(order: OrderEntity) {
        if (!order.status.isConfirmed()) throw InvilliaException.ValidationException(InvilliaErrorCode.NOT_REFUND_ALLOWED)
    }
}
