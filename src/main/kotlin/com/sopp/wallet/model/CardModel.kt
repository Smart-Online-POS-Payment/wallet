package com.sopp.wallet.model

import com.sopp.wallet.entity.CardEntity
import java.time.LocalDate

data class CardModel(
    val cardNumber: String,
    val cardHolderName: String,
    val cvvCode: String,
    val expiryDate: LocalDate,
){
    constructor(cardEntity: CardEntity): this(cardEntity.cardNumber, cardEntity.cardHolderName, cardEntity.cvvCode, cardEntity.expiryDate)
}
