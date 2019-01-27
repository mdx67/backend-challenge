package com.invillia.acme.mapper

import com.invillia.acme.api.CreatedStoreData
import com.invillia.acme.domain.entity.StoreEntity

fun StoreEntity.toCreatedStoreData(): CreatedStoreData {
    return CreatedStoreData(
        storeId = this.storeId,
        name = this.name,
        address = this.address
    )
}
