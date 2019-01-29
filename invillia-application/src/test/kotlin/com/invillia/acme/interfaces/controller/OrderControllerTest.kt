package com.invillia.acme.interfaces.controller

import com.invillia.acme.InvilliaTestBase
import com.invillia.acme.RestDocsHelper
import com.invillia.acme.domain.entity.OrderEntity
import com.invillia.acme.domain.entity.OrderItemEntity
import com.invillia.acme.domain.entity.OrderStatus
import com.invillia.acme.domain.repository.OrderRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@SpringBootTest
class OrderControllerTest : InvilliaTestBase() {

    @Autowired
    lateinit var orderRepository: OrderRepository

    @Before
    fun beforeTest() {
        val orders = listOf(
            OrderEntity(
                orderId = "ORDER-7d3d3300-4140-4e5a-bc75-ebf3b00fd34f",
                address = "Malibu Point, 10880",
                confirmationDate = null,
                status = OrderStatus.PENDING,
                orderItems = mutableListOf()
            ),
            OrderEntity(
                orderId = "ORDER-673d3300-4140-4e5a-bc75-ebf3b00fd34f",
                address = "Malibu Point, 10880",
                confirmationDate = LocalDateTime.now(),
                status = OrderStatus.CONFIRMED,
                orderItems = mutableListOf(
                    OrderItemEntity(
                        orderItemId = "ORDER-ITEM-7d3d3300-4140-4e5a-bc75-ebf3b00fd34f",
                        description = "description",
                        amount = 100,
                        quantity = 1
                    )
                )
            ),
            OrderEntity(
                orderId = "ORDER-683d3300-4140-4e5a-bc75-ebf3b00fd34f",
                address = "Malibu Point, 10880",
                confirmationDate = LocalDateTime.now(),
                status = OrderStatus.CONFIRMED,
                orderItems = mutableListOf(
                    OrderItemEntity(
                        orderItemId = "ORDER-ITEM-683d3300-4140-4e5a-bc75-ebf3b00fd34f",
                        description = "description",
                        amount = 100,
                        quantity = 1
                    )
                )
            )
        )

        orderRepository.save(orders)
    }

    @Test
    fun `should create an order with success`() {
        this.mockMvc.perform(
            post("/v1/order")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getPayload("payload/create-order-request.json"))
        )
            .andDo(print())
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.orderId").isNotEmpty)
            .andExpect(jsonPath("$.address").isNotEmpty)
            .andExpect(jsonPath("$.status").value("PENDING"))
            .andDo(RestDocsHelper.orderDocument("create-order-success"))
            .andReturn()
    }

    @Test
    fun `should add an item to order with success`() {
        this.mockMvc.perform(
            post("/v1/order/item/ORDER-7d3d3300-4140-4e5a-bc75-ebf3b00fd34f")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getPayload("payload/add-order-item-request.json"))
        )
            .andDo(print())
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.orderId").isNotEmpty)
            .andExpect(jsonPath("$.address").isNotEmpty)
            .andExpect(jsonPath("$.confirmationDate").isNotEmpty)
            .andExpect(jsonPath("$.items[0]").isNotEmpty)
            .andExpect(jsonPath("$.status").value("PENDING"))
            .andDo(RestDocsHelper.orderItemDocument("add-order-item-success"))
            .andReturn()
    }

    @Test
    fun `should get an order`() {
        this.mockMvc.perform(
            get("/v1/order/ORDER-7d3d3300-4140-4e5a-bc75-ebf3b00fd34f")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andDo(RestDocsHelper.orderResponseDocument("find-order-success"))
            .andReturn()
    }

    @Test
    fun `should refund an order with success`() {
        this.mockMvc.perform(
            delete("/v1/order/ORDER-683d3300-4140-4e5a-bc75-ebf3b00fd34f")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
            .andDo(print())
            .andExpect(status().isAccepted)
            .andExpect(jsonPath("$.orderId").isNotEmpty)
            .andExpect(jsonPath("$.address").isNotEmpty)
            .andExpect(jsonPath("$.confirmationDate").isNotEmpty)
            .andExpect(jsonPath("$.status").value("REFUNDED"))
            .andDo(RestDocsHelper.orderResponseDocument("refund-success"))
            .andReturn()
    }

    @Test
    fun `should refund an item with success`() {
        this.mockMvc.perform(
            delete("/v1/order/ORDER-673d3300-4140-4e5a-bc75-ebf3b00fd34f/item/ORDER-ITEM-7d3d3300-4140-4e5a-bc75-ebf3b00fd34f")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
            .andDo(print())
            .andExpect(status().isAccepted)
            .andExpect(jsonPath("$.orderId").isNotEmpty)
            .andExpect(jsonPath("$.address").isNotEmpty)
            .andExpect(jsonPath("$.confirmationDate").isNotEmpty)
            .andExpect(jsonPath("$.status").value("PARTIAL_REFUNDED"))
            .andDo(RestDocsHelper.orderResponseDocument("refund-item-success"))
            .andReturn()
    }
}
