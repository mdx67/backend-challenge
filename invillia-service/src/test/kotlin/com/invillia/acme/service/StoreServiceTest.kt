package com.invillia.acme.service

import com.invillia.acme.api.CreateStoreData
import com.invillia.acme.domain.StoreRepository
import com.invillia.acme.domain.entity.StoreEntity
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers

class StoreServiceTest {

    private lateinit var storeService: StoreService

    private lateinit var storeRepositoryMock: StoreRepository

    @Before
    fun setUp() {
        val storeEntityOk = StoreEntity("STORE-123", "Store name", "Store address")

        storeRepositoryMock = mock {
            on { save(ArgumentMatchers.any<StoreEntity>()) } doReturn storeEntityOk
        }

        storeService = StoreService(storeRepositoryMock)
    }

    @Test
    fun `should create a store with success`() {
        val createStore = CreateStoreData("Store name", "Store address")

        val createdStore = storeService.create(createStore)

        assertNotNull(createdStore)
        assertEquals("Store name", createdStore.name)
        assertEquals("Store address", createdStore.address)
    }

}
