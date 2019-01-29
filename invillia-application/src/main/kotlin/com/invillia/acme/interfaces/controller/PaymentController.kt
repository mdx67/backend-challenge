package com.invillia.acme.interfaces.controller

import com.invillia.acme.api.CreatePaymentData
import com.invillia.acme.api.PaymentApi
import com.invillia.acme.api.PaymentData
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import javax.validation.Valid

@RestController
@RequestMapping(PaymentController.ROOT_URL)
class PaymentController(
//    private val payemntService: PaymentService
) : PaymentApi {


    var LOG = LoggerFactory.getLogger(PaymentController::class.java)

    companion object {
        const val ROOT_URL = "/v1/payment"
    }

    override fun perform(@Valid @RequestBody request: CreatePaymentData): PaymentData {
        return PaymentData("PAY-123", "ORDER-123", "CONFIRMED", "123456", LocalDateTime.now())
    }

}
