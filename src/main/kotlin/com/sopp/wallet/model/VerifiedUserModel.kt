package com.sopp.wallet.model

data class VerifiedUserModel(
    val name: String,
    val surname: String,
    val citizenId: String,
    val gsmNumber: String,
    val email: String,
    val registrationAddress: String,
    val ip: String,
    val buyerId: String,
    val userAgent: String,
)
