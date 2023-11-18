package com.sopp.wallet.exception

class WalletNotFoundException(message: String) : RuntimeException(message) {

}

class PaymentExceedsBalanceException(message: String): RuntimeException(message) {

}