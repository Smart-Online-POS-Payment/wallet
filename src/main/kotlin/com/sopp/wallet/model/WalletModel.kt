package com.sopp.wallet.model

import java.math.BigDecimal

data class WalletModel(
    val customerId: String,
    val balance: BigDecimal
)
