package com.sopp.wallet.model

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.sopp.wallet.entity.CardEntity
import com.sopp.wallet.util.ShortDateDeserializer
import com.sopp.wallet.util.ShortDateSerializer
import java.time.LocalDate

data class CardModel(
    val cardholderName: String,
    val cardNumber: String,
    val cvvCode: String,
    val expiryDate: String
){
    constructor(cardEntity: CardEntity):this(cardholderName = cardEntity.cardholderName, cardNumber = cardEntity.cardNumber, cvvCode= cardEntity.cvvCode, expiryDate = cardEntity.expiryDate)
}

