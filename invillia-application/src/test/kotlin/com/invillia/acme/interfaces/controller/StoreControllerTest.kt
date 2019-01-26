package com.invillia.acme.interfaces.controller

import com.invillia.acme.InvilliaTestBase
import com.invillia.acme.RestDocsHelper
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@SpringBootTest
class StoreControllerTest : InvilliaTestBase() {

    @Test
    fun `should create store with success`() {
        this.mockMvc.perform(
            post("/v1/store")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getPayload("payload/create-store-request.json"))
        )
            .andDo(print())
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.storeId").isNotEmpty)
            .andExpect(jsonPath("$.name").isNotEmpty)
            .andExpect(jsonPath("$.address").isNotEmpty)
            .andDo(RestDocsHelper.storeDocument("create-store-success"))
            .andReturn()
    }

}
