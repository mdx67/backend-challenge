package com.invillia.acme.service

import com.invillia.acme.api.CreatePaymentData
import com.invillia.acme.api.PaymentData
import com.invillia.acme.domain.PaymentId
import com.invillia.acme.domain.entity.OrderEntity
import com.invillia.acme.domain.entity.PaymentEntity
import com.invillia.acme.domain.entity.PaymentStatus
import com.invillia.acme.domain.repository.PaymentRepository
import com.invillia.acme.mapper.toPaymentData
import com.invillia.acme.validation.PaymentValidations
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val paymentValidations: PaymentValidations,
    private val orderService: OrderService
) {

    private val LOG: Logger = LoggerFactory.getLogger(this.javaClass)

    fun perform(request: CreatePaymentData): PaymentData {
        paymentValidations.validPayment(request)

        val order = orderService.getEntityById(request.orderId!!)
        val paymentEntity: PaymentEntity?

        paymentEntity = if (request.async) performScheduledPayment(request, order) else {
            performNormalPayment(request, order)
        }

        return paymentRepository.save(paymentEntity).toPaymentData()
    }

    private fun performScheduledPayment(payment: CreatePaymentData, order: OrderEntity): PaymentEntity {
        return PaymentEntity(
            paymentId = PaymentId().id,
            order = order,
            status = PaymentStatus.SCHEDULED,
            creditCardNumber = payment.creditCardNumber!!
        )
    }

    private fun performNormalPayment(payment: CreatePaymentData, order: OrderEntity): PaymentEntity {
        //TODO: call payments-connector to perform payment

        return PaymentEntity(
            paymentId = PaymentId().id,
            order = order,
            status = PaymentStatus.CONFIRMED,
            paymentDate = LocalDateTime.now(),
            creditCardNumber = payment.creditCardNumber!!
        )
    }

    fun processPendingPayments() {
        val pendingPayments = paymentRepository.findByStatus(PaymentStatus.SCHEDULED)

        pendingPayments.forEach { payment ->
            //TODO: call payments-connector to perform payment

            LOG.info("Payment id: ${payment.paymentId} was confirmed!")

            payment.status = PaymentStatus.CONFIRMED

            paymentRepository.save(payment)
        }
    }
}
