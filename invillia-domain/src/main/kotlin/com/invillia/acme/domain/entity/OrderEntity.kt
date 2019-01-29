package com.invillia.acme.domain.entity

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity(name = "orders")
data class OrderEntity(

    @Id
    @Column(name = "order_id")
    val orderId: String = "",
    val address: String = "",
    @Column(name = "confirmation_date")
    val confirmationDate: LocalDateTime? = null,
    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.PENDING,
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "order", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    var orderItems: MutableList<OrderItemEntity>? = null
)

@Entity(name = "order_item")
data class OrderItemEntity(

    @Id
    @Column(name = "order_item_id")
    val orderItemId: String = "",
    val description: String? = "",
    val amount: Int = 0,
    val quantity: Int = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: OrderEntity? = null
)

enum class OrderStatus {
    PENDING, CONFIRMED, REFUNDED, PARTIAL_REFUNDED;

    fun isConfirmed(): Boolean {
        return this == CONFIRMED
    }
}
