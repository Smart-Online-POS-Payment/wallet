package com.sopp.wallet.model

import com.sopp.wallet.entity.CardEntity

data class CardModel(
    val cardNumber: String,
    val cardHolderName: String,
    val cvvCode: String,
    val expiryDate: String,
) {
    constructor(
        cardEntity: CardEntity,
    ) : this(cardEntity.cardNumber, cardEntity.cardHolderName, cardEntity.cvvCode, cardEntity.expiryDate.toString())
}
