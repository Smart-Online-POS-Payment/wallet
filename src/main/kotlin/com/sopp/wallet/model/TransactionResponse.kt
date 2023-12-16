package com.sopp.wallet.model

import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

data class TransactionResponse(
    val id: UUID,
    val type: String,
    val date: Instant,
    val amount: BigDecimal,
    val currency: String,
    val operation: Operation,
    val response: Map<String, Any>? = null,
    val error: BankError? = null,
)
