package com.invillia.acme.service

import com.invillia.acme.api.CreateStoreData
import com.invillia.acme.api.CreatedStoreData
import com.invillia.acme.api.UpdateStoreData
import com.invillia.acme.domain.StoreId
import com.invillia.acme.domain.StoreRepository
import com.invillia.acme.domain.entity.StoreEntity
import com.invillia.acme.infra.InvilliaException
import com.invillia.acme.mapper.toCreatedStoreData
import org.springframework.stereotype.Service

@Service
class StoreService(
    private val storeRepository: StoreRepository
) {

    fun create(storeData: CreateStoreData): CreatedStoreData {
        val store = StoreEntity(
            storeId = StoreId().id,
            name = storeData.name!!,
            address = storeData.address!!
        )

        return storeRepository.save(store).toCreatedStoreData()
    }

    fun update(updateData: UpdateStoreData) {
        val store = StoreEntity(
            storeId = StoreId().id,
            name = updateData.name!!,
            address = updateData.address!!
        )

        storeRepository.save(store)
    }

    fun getById(id: String): CreatedStoreData {
        val store: StoreEntity? = storeRepository.findOne(id)
                ?: throw InvilliaException.NotFoundException(id)
        
        return store!!.toCreatedStoreData()
    }
}
