package com.invillia.acme.interfaces.controller

import com.invillia.acme.api.CreateOrderData
import com.invillia.acme.api.CreateOrderItemData
import com.invillia.acme.api.CreatedOrderData
import com.invillia.acme.api.CreatedOrderItemData
import com.invillia.acme.api.OrderApi
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import javax.validation.Valid

@RestController
@RequestMapping(OrderController.ROOT_URL)
class OrderController(
//    private val storeService: StoreService
) : OrderApi {

    var LOG = LoggerFactory.getLogger(OrderController::class.java)

    companion object {
        const val ROOT_URL = "/v1/order"
    }

    override fun create(@Valid @RequestBody request: CreateOrderData): CreatedOrderData {
        LOG.info("Create new order.")

        return CreatedOrderData(
            "id",
            "address",
            LocalDateTime.now(),
            "PENDING",
            listOf(CreatedOrderItemData("orderId", "description", 100, 1)),
            LocalDateTime.now()
        )
    }

    override fun addItem(
        @PathVariable("orderId") orderId: String,
        @Valid @RequestBody request: CreateOrderItemData
    ): CreatedOrderData {
        LOG.info("Add new item to order id: $orderId.")

        return CreatedOrderData(
            "id",
            "address",
            LocalDateTime.now(),
            "CONFIRMED",
            listOf(CreatedOrderItemData("id", "description", 100, 1)),
            LocalDateTime.now()
        )
    }

    override fun refund(@PathVariable("id") id: String): CreatedOrderData {
        LOG.info("Refund order, id: $id.")

        return CreatedOrderData(
            "id",
            "address",
            LocalDateTime.now(),
            "REFUNDED",
            listOf(CreatedOrderItemData("id", "description", 100, 1)),
            LocalDateTime.now()
        )
    }

    override fun refundItem(@PathVariable("itemId") itemId: String): CreatedOrderData {
        LOG.info("Refund order item, id: $itemId.")

        return CreatedOrderData(
            "id",
            "address",
            LocalDateTime.now(),
            "REFUNDED",
            listOf(CreatedOrderItemData("id", "description", 100, 1)),
            LocalDateTime.now()
        )
    }

    override fun query(@PathVariable("id") id: String): CreatedOrderData {
        LOG.info("Query order, id: $id.")

        return CreatedOrderData(
            "id",
            "address",
            LocalDateTime.now(),
            "CONFIRMED",
            listOf(CreatedOrderItemData("id", "description", 100, 1)),
            LocalDateTime.now()
        )
    }
}
