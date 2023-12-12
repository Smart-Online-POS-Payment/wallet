package com.sopp.wallet.model

import java.io.Serializable

data class BankError(
    val code: String,
    val message: String,
    val upstreamCode: String?,
    val upstreamMessage: String?,
) : Serializable