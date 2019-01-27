package com.invillia.acme

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
@EnableJpaRepositories("com.invillia.acme.domain")
@EntityScan("com.invillia.acme.domain.entity")
@ComponentScan("com.invillia.acme")
class InvilliaConfig : WebMvcConfigurerAdapter()
