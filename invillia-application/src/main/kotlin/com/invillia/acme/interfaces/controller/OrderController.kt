package com.invillia.acme.interfaces.controller

import com.invillia.acme.api.CreateOrderData
import com.invillia.acme.api.CreateOrderItemData
import com.invillia.acme.api.CreatedOrderData
import com.invillia.acme.api.OrderApi
import com.invillia.acme.service.OrderService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(OrderController.ROOT_URL)
class OrderController(
    private val orderService: OrderService
) : OrderApi {

    var LOG = LoggerFactory.getLogger(OrderController::class.java)

    companion object {
        const val ROOT_URL = "/v1/order"
    }

    override fun create(@Valid @RequestBody request: CreateOrderData): CreatedOrderData {
        LOG.info("Create new order.")

        return orderService.create(request)
    }

    override fun addItem(
        @PathVariable("orderId") orderId: String,
        @Valid @RequestBody request: CreateOrderItemData
    ): CreatedOrderData {
        LOG.info("Add new item to order id: $orderId.")

        return orderService.addItem(orderId, request)
    }

    override fun refund(@PathVariable("id") id: String): CreatedOrderData {
        LOG.info("Refund order, id: $id.")

        return orderService.refund(id)
    }

    override fun refundItem(
        @PathVariable("id") orderId: String,
        @PathVariable("itemId") itemId: String
    ): CreatedOrderData {
        LOG.info("Refund order id: $orderId, item id: $itemId.")

        return orderService.refundItem(orderId, itemId)
    }

    override fun query(@PathVariable("id") id: String): CreatedOrderData {
        LOG.info("Query order, id: $id.")

        return orderService.getById(id)
    }
}
