package com.invillia.acme.api

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.validation.Valid

interface OrderApi {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @Valid @RequestBody request: CreateOrderData
    ): CreatedOrderData

    @PostMapping(
        value = ["/item/{orderId}"],
        consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.CREATED)
    fun addItem(
        @PathVariable("orderId") orderId: String,
        @Valid @RequestBody request: CreateOrderItemData
    ): CreatedOrderData

    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun query(
        @PathVariable("id") id: String
    ): CreatedOrderData

    @DeleteMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun refund(
        @PathVariable("id") id: String
    ): CreatedOrderData

    @DeleteMapping(value = ["/{id}/item/{itemId}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun refundItem(
        @PathVariable("id") orderId: String,
        @PathVariable("itemId") itemId: String
    ): CreatedOrderData

}
