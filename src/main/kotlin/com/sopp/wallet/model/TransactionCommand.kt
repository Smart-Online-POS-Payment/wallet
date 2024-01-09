package com.sopp.wallet.model

import java.math.BigDecimal

data class TransactionCommand(
    val orderId: String,
    val amount: BigDecimal
)
