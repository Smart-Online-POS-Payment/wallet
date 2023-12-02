package com.sopp.wallet.repository

import com.sopp.wallet.entity.CardEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CardRepository: CrudRepository<CardEntity, UUID> {
    fun findByCustomerId(customerId: String): List<CardEntity>?
}