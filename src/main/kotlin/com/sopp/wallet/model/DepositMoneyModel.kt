package com.sopp.wallet.model

data class DepositMoneyModel(
    val command: TransactionCommand,
    val card: CardModel,
    val buyerInformation: BuyerInformation,
    val billingAddress: Address,
    val basketItems: List<BasketItem>
)
