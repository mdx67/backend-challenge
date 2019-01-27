package com.invillia.acme.domain.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "store")
data class StoreEntity(

    @Id
    val storeId: String = "",
    val name: String = "",
    val address: String = ""
)
