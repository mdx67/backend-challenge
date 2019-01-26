package com.invillia.acme

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.invillia.acme")
class InvilliaConfig : WebMvcConfigurerAdapter()
