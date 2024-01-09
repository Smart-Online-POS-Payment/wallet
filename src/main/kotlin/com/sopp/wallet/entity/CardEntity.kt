package com.sopp.wallet.entity

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.sopp.wallet.model.CardModel
import com.sopp.wallet.util.ShortDateDeserializer
import com.sopp.wallet.util.ShortDateSerializer
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "card")
data class CardEntity(
    @Id
    val id: UUID = UUID.randomUUID(),
    val customerId: String,
    val cardNumber: String,
    val cardholderName: String,
    val cvvCode: String,
    val expiryDate: String,
) {
    constructor(
        customerId: String,
        cardModel: CardModel,
    ) : this(UUID.randomUUID(), customerId, cardModel.cardNumber, cardModel.cardholderName, cardModel.cvvCode, cardModel.expiryDate)
    constructor() : this(UUID.randomUUID(), "", "", "", "", LocalDate.now().toString())
}
