package com.sopp.wallet.service

import com.sopp.wallet.client.IstepayClient
import com.sopp.wallet.model.CardModel
import com.sopp.wallet.model.DepositMoneyModel
import com.sopp.wallet.model.DepositResponseModel
import com.sopp.wallet.model.TransactionCommand
import com.sopp.wallet.model.VerifiedUserModel
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
                buyerInformation = user,
                merchantId = UUID.fromString(""),
            )
        return istepayClient.depositMoney(depositMoneyModel)
    }
}
