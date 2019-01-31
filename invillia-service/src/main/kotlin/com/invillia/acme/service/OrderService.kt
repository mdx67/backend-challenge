package com.invillia.acme.service

import com.invillia.acme.api.CreateOrderData
import com.invillia.acme.api.CreateOrderItemData
import com.invillia.acme.api.CreatedOrderData
import com.invillia.acme.domain.OrderId
import com.invillia.acme.domain.OrderItemId
import com.invillia.acme.domain.entity.OrderEntity
import com.invillia.acme.domain.entity.OrderItemEntity
import com.invillia.acme.domain.entity.OrderStatus
import com.invillia.acme.domain.repository.OrderRepository
import com.invillia.acme.infra.InvilliaException
import com.invillia.acme.infra.error.InvilliaErrorCode
import com.invillia.acme.mapper.toCreatedOrderData
import com.invillia.acme.validation.OrderValidations
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val orderValidations: OrderValidations
) {

    fun create(storeData: CreateOrderData): CreatedOrderData {
        val order = OrderEntity(
            orderId = OrderId().id,
            address = storeData.address!!,
            status = OrderStatus.PENDING
        )

        order.orderItems = buildNewOrderItemEntity(order, storeData.items)

        return orderRepository.save(order).toCreatedOrderData()
    }

    fun refund(orderId: String): CreatedOrderData {
        val order: OrderEntity = getEntityById(orderId)

        orderValidations.validCanRefund(order)

        //TODO: integration with payment-connector

        order.status = OrderStatus.REFUNDED

        return orderRepository.save(order).toCreatedOrderData()
    }

    fun refundItem(orderId: String, itemId: String): CreatedOrderData {
        val order: OrderEntity = getEntityById(orderId)

        orderValidations.validCanRefund(order)

        //TODO: integration with payment-connector

        order.status = OrderStatus.PARTIAL_REFUNDED

        return orderRepository.save(order).toCreatedOrderData()
    }

    private fun buildNewOrderItemEntity(
        order: OrderEntity,
        orderItems: List<CreateOrderItemData>?
    ): MutableList<OrderItemEntity> {
        val result: MutableList<OrderItemEntity> = mutableListOf()

        orderItems?.forEach {
            result.add(
                OrderItemEntity(
                    orderItemId = OrderItemId().id, order = order, description = it.description,
                    amount = it.amount!!, quantity = it.quantity!!
                )
            )
        }

        return result
    }

    fun addItem(orderId: String, orderItem: CreateOrderItemData): CreatedOrderData {
        val order: OrderEntity = getEntityById(orderId)

        val newOrder = OrderItemEntity(
            orderItemId = OrderItemId().id, order = order, description = orderItem.description,
            amount = orderItem.amount!!, quantity = orderItem.quantity!!
        )

        order.orderItems?.add(newOrder)

        return orderRepository.save(order).toCreatedOrderData()
    }

    fun getById(id: String): CreatedOrderData {
        return getEntityById(id).toCreatedOrderData()
    }

    fun getEntityById(id: String): OrderEntity {
        return orderRepository.findOne(id)
                ?: throw InvilliaException.NotFoundException(InvilliaErrorCode.NOT_FOUND_ERROR, id)
    }
}
