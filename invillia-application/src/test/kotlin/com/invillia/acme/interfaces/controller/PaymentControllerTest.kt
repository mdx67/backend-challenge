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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@SpringBootTest
class PaymentControllerTest : InvilliaTestBase() {

    @Autowired
    lateinit var orderRepository: OrderRepository

    @Before
    fun beforeTest() {
        val orders = listOf(
            OrderEntity(
                orderId = "ORDER-683d3300-4140-4e5a-bc75-ebf3b00fddae",
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
                        orderItemId = "ORDER-ITEM-7d3d3300-414df5-5a-bc75-ebf3b00fd34f",
                        description = "description",
                        amount = 100,
                        quantity = 1
                    )
                )
            ),
            OrderEntity(
                orderId = "ORDER-683d3300-4140-4e5a-bc75-ebf3bddfd34f",
                address = "Malibu Point, 10880",
                confirmationDate = LocalDateTime.now(),
                status = OrderStatus.CONFIRMED,
                orderItems = mutableListOf(
                    OrderItemEntity(
                        orderItemId = "ORDER-ITEM-683d3300-4140-4e5a-bc75-eddaf3b00fd34f",
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
    fun `should create payment scheduled with success`() {
        this.mockMvc.perform(
            post("/v1/payment")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getPayload("payload/create-payment-scheduled-request.json"))
        )
            .andDo(print())
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.paymentId").isNotEmpty)
            .andExpect(jsonPath("$.orderId").isNotEmpty)
            .andExpect(jsonPath("$.status").value("SCHEDULED"))
            .andExpect(jsonPath("$.creditCardNumber").isNotEmpty)
            .andDo(RestDocsHelper.paymentDocument("create-payment-scheduled-success"))
            .andReturn()
    }

    @Test
    fun `should create payment normal with success`() {
        this.mockMvc.perform(
            post("/v1/payment")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getPayload("payload/create-payment-normal-request.json"))
        )
            .andDo(print())
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.paymentId").isNotEmpty)
            .andExpect(jsonPath("$.orderId").isNotEmpty)
            .andExpect(jsonPath("$.status").value("CONFIRMED"))
            .andExpect(jsonPath("$.creditCardNumber").isNotEmpty)
            .andExpect(jsonPath("$.paymentDate").isNotEmpty)
            .andDo(RestDocsHelper.paymentDocument("create-payment-normal-success"))
            .andReturn()
    }
}
