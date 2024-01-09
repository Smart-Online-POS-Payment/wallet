package com.sopp.wallet.model

import java.time.Instant

data class VerifiedUserModel(
    val userId: String,
    val tc: Long,
    val firstname: String,
    val surname: String,
    val email: String,
    val phoneNumber: String,
    val openAddress: String,
    val city: String,
    val country: String,
)
