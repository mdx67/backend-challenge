package com.invillia.acme.domain

import com.invillia.acme.domain.entity.StoreEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository : JpaRepository<StoreEntity, String>
