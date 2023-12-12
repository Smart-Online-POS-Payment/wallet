package com.sopp.wallet.model

data class BillingAddress(
    val contactName: String,
    val city: String,
    val country: String,
    val address: String,
    val zipCode: String? = null,
)