package com.invillia.acme.interfaces.controller

import com.invillia.acme.InvilliaTestBase
import com.invillia.acme.RestDocsHelper
import com.invillia.acme.domain.repository.StoreRepository
import com.invillia.acme.domain.entity.StoreEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@SpringBootTest
class PaymentControllerTest : InvilliaTestBase() {

//    @Autowired
//    lateinit var storeRepository: StoreRepository

    @Before
    fun beforeTest() {
//        val stores = listOf(
//            StoreEntity(
//                storeId = "STORE-7d3d3300-4140-4e5a-bc75-ebf3b00fd34f",
//                name = "Stark International",
//                address = "Malibu Point, 10880"
//            ),
//            StoreEntity(
//                storeId = "STORE-8899eb4c-8907-4735-a8f4-e1f2166a9713",
//                name = "Avengers",
//                address = "Street one, 0"
//            )
//        )
//        storeRepository.save(stores)
    }

    @Test
    fun `should create payment with success`() {
        this.mockMvc.perform(
            post("/v1/payment")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getPayload("payload/create-payment-request.json"))
        )
            .andDo(print())
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.paymentId").isNotEmpty)
            .andExpect(jsonPath("$.orderId").isNotEmpty)
            .andExpect(jsonPath("$.status").isNotEmpty)
            .andExpect(jsonPath("$.creditCardNumber").isNotEmpty)
            .andExpect(jsonPath("$.paymentDate").isNotEmpty)
            .andDo(RestDocsHelper.paymentDocument("create-payment-success"))
            .andReturn()
    }

}
