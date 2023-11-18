package com.sopp.wallet.entity

import com.sopp.wallet.model.WalletModel
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "wallet")
data class WalletEntity(
    @Id
    val id: UUID = UUID.randomUUID(),
    val customerId: String,
    var balance: BigDecimal
){
    constructor(): this(UUID.randomUUID(), UUID.randomUUID().toString(), BigDecimal(0))
    constructor(walletModel: WalletModel): this(UUID.randomUUID(), walletModel.customerId, walletModel.balance)
}