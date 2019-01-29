package com.invillia.acme.validation

import com.invillia.acme.domain.entity.OrderEntity
import com.invillia.acme.infra.InvilliaException
import org.springframework.stereotype.Component

@Component
class OrderValidations {

    fun validCanRefund(order: OrderEntity) {
        if (!order.status.isConfirmed()) throw InvilliaException.ValidationException("Não é possível estornar uma ordem não confirmada.")
    }
}
