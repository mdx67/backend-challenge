package com.invillia.acme.job.task

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.support.CronTrigger
import org.springframework.stereotype.Component

@Component
class PaymentProcessScheduler(
    private val taskScheduler: TaskScheduler,
    private val appContext: ApplicationContext
) {

    private val LOG: Logger = LoggerFactory.getLogger(this.javaClass)

    @Value("\${scheduler.payment.process.cron}")
    lateinit var cron: String

    fun schedule() {
        try {
            val task: PaymentProcessTask =
                appContext.getBean(PaymentProcessTask.COMPONENT_NAME, PaymentProcessTask::class.java)

            taskScheduler.schedule(task, CronTrigger(cron))

            LOG.info("Payment process task scheduled with cron $cron.")
        } catch (e: Exception) {
            LOG.warn("Task for Payment process can't be scheduled.", e)
        }
    }
}
