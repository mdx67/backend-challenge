package com.invillia.acme.domain

import java.util.UUID

class StoreId (
    val id: String = "STORE-${UUID.randomUUID()}"
)
