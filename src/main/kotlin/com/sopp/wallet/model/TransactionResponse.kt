package com.sopp.wallet.model

import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

data class TransactionResponse(
    val id: UUID,
    val orderId: String,
    val type: Type,
    val status: Status,
    val date: Instant,
    val amount: BigDecimal,
    val currency: CurrencyCode = CurrencyCode.TRY,
    val error: Error? = null
) {
    enum class Type {
        Auth
    }

    enum class Status {
        Processing,
        SecurePageGenerated,
        Succeeded,
        Failed,
        Unknown,
        Cancelled
    }
}

