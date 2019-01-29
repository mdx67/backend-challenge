package com.invillia.acme.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "stores")
data class StoreEntity(

    @Id
    @Column(name = "store_id")
    val storeId: String = "",
    val name: String = "",
    val address: String = ""
)
