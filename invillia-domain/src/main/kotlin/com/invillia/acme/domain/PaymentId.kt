package com.invillia.acme.domain

import java.util.UUID

class PaymentId (
    val id: String = "PAY-${UUID.randomUUID()}"
)
