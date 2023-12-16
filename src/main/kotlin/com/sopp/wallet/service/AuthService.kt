package com.sopp.wallet.service

import com.sopp.wallet.client.AuthClient
import com.sopp.wallet.model.VerifiedUserModel
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authClient: AuthClient,
) {
    suspend fun getUser(customerId: String): VerifiedUserModel {
        return authClient.getUser(customerId)
    }
}
