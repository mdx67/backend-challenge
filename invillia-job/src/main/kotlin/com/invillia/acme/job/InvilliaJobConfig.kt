package com.invillia.acme.job

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
@EnableScheduling
@EnableJpaRepositories("com.invillia.acme.domain")
@EntityScan("com.invillia.acme.domain.entity")
@ComponentScan("com.invillia.acme")
class InvilliaJobConfig : WebMvcConfigurerAdapter() {

    @Bean
    fun taskScheduler(@Value("\${scheduler.pool.size}") schedulerPoolSize: Int): TaskScheduler {
        val taskScheduler = ThreadPoolTaskScheduler()
        taskScheduler.poolSize = schedulerPoolSize
        return taskScheduler
    }
}
