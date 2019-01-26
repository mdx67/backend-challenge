package com.invillia.acme

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ServletComponentScan
@EnableAutoConfiguration
@ComponentScan("com.invillia.acme")
class InvilliaConfig
