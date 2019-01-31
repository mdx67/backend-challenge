package com.invillia.acme.domain.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "payments")
data class PaymentEntity(

    @Id
    @Column(name = "payment_id")
    val paymentId: String = "",
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: OrderEntity? = null,
    @Enumerated(EnumType.STRING)
    var status: PaymentStatus = PaymentStatus.PENDING,
    @JoinColumn(name = "credit_card_number")
    val creditCardNumber: String = "",
    @Column(name = "payment_date")
    val paymentDate: LocalDateTime? = null
)

enum class PaymentStatus {
    PENDING, SCHEDULED, AUTHORIZED, CONFIRMED, REFUNDED, PARTIAL_REFUNDED;
}
