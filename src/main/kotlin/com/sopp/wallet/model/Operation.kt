package com.sopp.wallet.model

import java.time.Instant

data class Operation(
    val status: Status,
    val result: String?,
    val completed: Boolean,
    val succeeded: Boolean,
    val createdDate: Instant?,
) {
    enum class Status {
        Created,
        Unknown,
        Failed,
        Done,
    }
}
