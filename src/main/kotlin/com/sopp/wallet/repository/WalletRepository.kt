package com.sopp.wallet.repository

import com.sopp.wallet.entity.WalletEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface WalletRepository : CrudRepository<WalletEntity, UUID> {
    fun findByCustomerId(customerId: String): WalletEntity?
}