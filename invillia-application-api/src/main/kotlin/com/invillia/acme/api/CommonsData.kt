package com.invillia.acme.api

import org.hibernate.validator.constraints.NotEmpty
import java.time.LocalDateTime
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class CreateStoreData(
    @field:[NotEmpty] val name: String?,
    @field:[NotEmpty] val address: String?
)

data class UpdateStoreData(
    @field:[NotEmpty] val name: String?,
    @field:[NotEmpty] val address: String?
)

data class CreatedStoreData(
    val storeId: String,
    val name: String?,
    val address: String?
)

data class OrderData(
    @field:[NotEmpty] val address: String?,
    @field:[NotEmpty] val confirmationDate: LocalDateTime?,
    @field:[NotEmpty] val status: String?
)

data class OrderItemData(
    val description: String?,
    @field:[Valid NotNull] val price: PriceData?,
    @field:[NotNull] val quantity: Int?
)

data class PaymentData(
    val description: String?,
    @field:[Valid NotNull] val price: PriceData?,
    @field:[NotNull] val quantity: Int?
)

data class PriceData(
    @field:[NotEmpty] val currency: String?,
    @field:[NotNull Positive] val amount: Int?,
    @field:[NotNull] val scale: Int?
)
