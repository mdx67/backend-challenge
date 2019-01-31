package com.invillia.acme.mapper

import com.invillia.acme.api.CreatedOrderData
import com.invillia.acme.api.CreatedOrderItemData
import com.invillia.acme.api.CreatedStoreData
import com.invillia.acme.api.PaymentData
import com.invillia.acme.domain.entity.OrderEntity
import com.invillia.acme.domain.entity.PaymentEntity
import com.invillia.acme.domain.entity.StoreEntity

fun StoreEntity.toCreatedStoreData(): CreatedStoreData {
    return CreatedStoreData(
        storeId = this.storeId,
        name = this.name,
        address = this.address
    )
}

fun OrderEntity.toCreatedOrderData(): CreatedOrderData {
    val items: MutableList<CreatedOrderItemData> = mutableListOf()

    this.orderItems?.forEach {
        items.add(
            CreatedOrderItemData(
                orderItemId = it.orderItemId, description = it.description,
                amount = it.amount, quantity = it.quantity
            )
        )
    }

    return CreatedOrderData(
        orderId = this.orderId,
        address = this.address,
        confirmationDate = this.confirmationDate,
        status = this.status.name,
        items = items
    )
}


fun PaymentEntity.toPaymentData(): PaymentData {
    return PaymentData(
        paymentId = this.paymentId,
        orderId = this.order!!.orderId,
        creditCardNumber = this.creditCardNumber,
        status = this.status.name,
        paymentDate = this.paymentDate
    )
}
