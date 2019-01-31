package com.invillia.acme.job

import com.invillia.acme.job.task.PaymentProcessScheduler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class InvilliaJobApplication : CommandLineRunner {

    @Autowired
    private lateinit var paymentProcessScheduler: PaymentProcessScheduler

    override fun run(vararg args: String?) {
        paymentProcessScheduler.schedule()
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(InvilliaJobApplication::class.java, *args)
}
