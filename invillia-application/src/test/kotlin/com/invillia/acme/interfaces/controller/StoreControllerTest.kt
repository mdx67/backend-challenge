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
class StoreControllerTest : InvilliaTestBase() {

    @Autowired
    lateinit var storeRepository: StoreRepository

    @Before
    fun beforeTest() {
        val stores = listOf(
            StoreEntity(
                storeId = "STORE-7d3d3300-4140-4e5a-bc75-ebf3b00fd34f",
                name = "Stark International",
                address = "Malibu Point, 10880"
            ),
            StoreEntity(
                storeId = "STORE-8899eb4c-8907-4735-a8f4-e1f2166a9713",
                name = "Avengers",
                address = "Street one, 0"
            )
        )
        storeRepository.save(stores)
    }

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

    @Test
    fun `should update store with success`() {
        this.mockMvc.perform(
            put("/v1/store/STORE-7d3d3300-4140-4e5a-bc75-ebf3b00fd34f")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getPayload("payload/update-store-request.json"))
        )
            .andDo(print())
            .andExpect(status().isAccepted)
            .andDo(RestDocsHelper.updateDocument("update-store-success"))
            .andReturn()
    }

    @Test
    fun `should not found store`() {
        this.mockMvc.perform(
            put("/v1/store/STORE-7d3d3300-4140-4e5a-bc75-NOT_FOUND")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getPayload("payload/update-store-request.json"))
        )
            .andDo(print())
            .andExpect(status().isNotFound)
            .andDo(RestDocsHelper.updateDocument("not-found-store"))
            .andReturn()
    }

    @Test
    fun `should get a store`() {
        this.mockMvc.perform(
            get("/v1/store/STORE-8899eb4c-8907-4735-a8f4-e1f2166a9713")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andDo(RestDocsHelper.getDocument("find-store-success"))
            .andReturn()
    }


}
