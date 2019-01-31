package com.invillia.acme.interfaces.controller

import com.invillia.acme.api.CreatePaymentData
import com.invillia.acme.api.PaymentApi
import com.invillia.acme.api.PaymentData
import com.invillia.acme.service.PaymentService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(PaymentController.ROOT_URL)
class PaymentController(
    private val paymentService: PaymentService
) : PaymentApi {

    var LOG = LoggerFactory.getLogger(PaymentController::class.java)

    companion object {
        const val ROOT_URL = "/v1/payment"
    }

    override fun perform(@Valid @RequestBody request: CreatePaymentData): PaymentData {
        LOG.info("Payment perform for order id: ${request.orderId}")

        return paymentService.perform(request)
    }
}
