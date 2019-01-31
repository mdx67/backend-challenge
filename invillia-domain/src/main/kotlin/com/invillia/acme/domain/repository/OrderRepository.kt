package com.invillia.acme.domain.repository

import com.invillia.acme.domain.entity.OrderEntity
import com.invillia.acme.domain.entity.OrderStatus
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<OrderEntity, String>
