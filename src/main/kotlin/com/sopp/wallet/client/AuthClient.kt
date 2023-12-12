package com.sopp.wallet.client

import com.sopp.wallet.model.VerifiedUserModel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class AuthClient(
    @Qualifier("authWebClient")
    val client: WebClient
) {
   suspend fun getUser(customerId:String): VerifiedUserModel {
       return client.get().uri("/customer/$customerId").retrieve().awaitBody()
   }
}