package com.invillia.acme.job.task

import com.invillia.acme.service.PaymentService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component(PaymentProcessTask.COMPONENT_NAME)
@Scope("prototype")
class PaymentProcessTask(
    private val paymentService: PaymentService
) : Runnable {

    companion object {
        const val COMPONENT_NAME: String = "paymentProcessTask"
    }

    private val LOG: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun run() {
        try {
            LOG.info("Initializing Payment job processing.")

            handle()
        } finally {
            LOG.info("Payment job processing finalized.")
        }
    }

    private tailrec fun handle() {
        paymentService.processPendingPayments()
    }


}
