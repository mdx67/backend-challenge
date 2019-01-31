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

data class CreateOrderData(
    @field:[NotEmpty] val address: String?,
    @field:[Valid] val items: List<CreateOrderItemData>?
)

data class CreateOrderItemData(
    val description: String?,
    @field:[NotNull Positive] val amount: Int?,
    @field:[NotNull] val quantity: Int?
)

data class CreatePaymentData(
    @field:[NotNull] val orderId: String?,
    @field:[NotNull] val creditCardNumber: String?,
    val async: Boolean = false
)

data class CreatedStoreData(
    val storeId: String,
    val name: String?,
    val address: String?
)

data class CreatedOrderData(
    val orderId: String,
    val address: String?,
    val confirmationDate: LocalDateTime?,
    val status: String?,
    val items: List<CreatedOrderItemData>? = null
)

data class CreatedOrderItemData(
    val orderItemId: String,
    val description: String?,
    val amount: Int?,
    val quantity: Int?
)

data class PaymentData(
    val paymentId: String,
    val orderId: String,
    val status: String?,
    val creditCardNumber: String?,
    val paymentDate: LocalDateTime?
)
