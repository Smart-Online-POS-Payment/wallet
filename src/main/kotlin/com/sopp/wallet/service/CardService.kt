package com.sopp.wallet.service

import com.sopp.wallet.entity.CardEntity
import com.sopp.wallet.model.CardModel
import com.sopp.wallet.repository.CardRepository
import org.springframework.stereotype.Service

@Service
class CardService(
    private val cardRepository: CardRepository,
) {
    fun getCards(customerId: String): List<CardModel> {
        return cardRepository.findByCustomerId(customerId)!!.map { CardModel(it) }
    }

    fun saveCard(
        customerId: String,
        cardModel: CardModel,
    ): CardEntity {
        // ToDo: Check if customer has this card
        return cardRepository.save(CardEntity(customerId, cardModel))
    }
}
