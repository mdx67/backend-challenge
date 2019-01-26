package com.invillia.acme.api

import br.com.zup.rw.boleto.command.api.BoletoCreatedData
import br.com.zup.rw.boleto.command.api.CreateBoletoData
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import javax.validation.Valid

interface OrderApi {

    @PostMapping(
        value = ["/order"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun create(
        @RequestHeader("x-customer-id") customerId: String,
        @Valid @RequestBody request: CreateBoletoData
    ): BoletoCreatedData
}
