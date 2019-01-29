package com.invillia.acme.domain

import java.util.UUID

class OrderId(
    val id: String = "ORDER-${UUID.randomUUID()}"
)

class OrderItemId(
    val id: String = "ORDER-ITEM-${UUID.randomUUID()}"
)
