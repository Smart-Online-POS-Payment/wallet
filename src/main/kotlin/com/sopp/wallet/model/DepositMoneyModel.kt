package com.sopp.wallet.model

import java.util.*

data class DepositMoneyModel(
    val transactionCommand: TransactionCommand,
    val card: CardModel,
    val buyerInformation: VerifiedUserModel? = null,
    val billingAddress: BillingAddress? = null,
    val merchantId: UUID,
)