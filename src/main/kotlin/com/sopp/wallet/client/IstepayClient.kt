package com.sopp.wallet.client

import com.sopp.wallet.model.DepositMoneyModel
import com.sopp.wallet.model.DepositResponseModel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class IstepayClient(
    @Qualifier("istepayWebClient")
    val client: WebClient,
) {
    suspend fun depositMoney(depositMoneyModel: DepositMoneyModel): DepositResponseModel {
        return client.post().uri("/payment").bodyValue(depositMoneyModel).retrieve().awaitBody()
    }
}
