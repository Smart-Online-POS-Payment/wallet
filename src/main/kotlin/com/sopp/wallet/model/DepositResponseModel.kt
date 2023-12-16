package com.sopp.wallet.model

data class DepositResponseModel(
    val orderId: String,
    val transaction: TransactionResponse,
)
