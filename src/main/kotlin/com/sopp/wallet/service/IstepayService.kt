package com.sopp.wallet.service

import com.sopp.wallet.client.IstepayClient
import com.sopp.wallet.model.*
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID

@Service
class IstepayService(
    private val istepayClient: IstepayClient,
) {
    suspend fun depositMoney(
        cardModel: CardModel,
        user: VerifiedUserModel,
        amount: BigDecimal,
    ): DepositResponseModel {
        val depositMoneyModel =
            DepositMoneyModel(
                transactionCommand = TransactionCommand(orderId = UUID.randomUUID().toString(), amount = amount),
                card = cardModel,
                buyerInformation = BuyerInformation(user),
                billingAddress = Address(user),
                basketItems = listOf(BasketItem(id = UUID.randomUUID().toString(), name = "deposit money", category = "finance", itemType = "Virtual", price = amount, productType = BasketItem.ProductType.Virtual))
                )
        return istepayClient.depositMoney(depositMoneyModel)
    }
}
