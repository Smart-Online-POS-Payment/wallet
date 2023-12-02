package com.sopp.wallet.entity

import com.sopp.wallet.model.CardModel
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "card")
data class CardEntity(
    @Id
    val id: UUID = UUID.randomUUID(),
    val customerId: String,
    val cardNumber: String,
    val cardHolderName: String,
    val cvvCode: String,
    val expiryDate: LocalDate,
){
    constructor(customerId: String, cardModel: CardModel): this(UUID.randomUUID(), customerId, cardModel.cardNumber, cardModel.cardHolderName, cardModel.cvvCode, cardModel.expiryDate)
    constructor() : this(UUID.randomUUID(), "", "", "", "", LocalDate.now())
}
