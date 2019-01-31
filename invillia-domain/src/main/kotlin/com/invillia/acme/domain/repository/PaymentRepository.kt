package com.invillia.acme.domain.repository

import com.invillia.acme.domain.entity.PaymentEntity
import com.invillia.acme.domain.entity.PaymentStatus
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository : JpaRepository<PaymentEntity, String> {

    fun findByStatus(status: PaymentStatus): List<PaymentEntity>
}
