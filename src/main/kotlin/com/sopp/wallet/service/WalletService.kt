package com.sopp.wallet.service

import com.sopp.wallet.entity.WalletEntity
import com.sopp.wallet.exception.NegativeAmountException
import com.sopp.wallet.exception.PaymentExceedsBalanceException
import com.sopp.wallet.exception.WalletNotFoundException
import com.sopp.wallet.repository.WalletRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class WalletService(
    private val walletRepository: WalletRepository
) {

    fun getWallet(customerId: String): WalletEntity {
        return walletRepository.findByCustomerId(customerId) ?: throw WalletNotFoundException("Cant find the wallet")
    }

    fun createWallet(customerId: String): WalletEntity {
        return walletRepository.save(WalletEntity(customerId = customerId, balance = BigDecimal.ZERO))
    }

    fun addMoneyToWallet(amount: BigDecimal, customerId: String){
        if(amount <= BigDecimal.ZERO) throw NegativeAmountException()

        val walletEntity = walletRepository.findByCustomerId(customerId)
            ?: throw WalletNotFoundException("Can't find the wallet")
        walletEntity.balance+=amount
        walletRepository.save(walletEntity)
    }

    fun withdrawMoney(amount: BigDecimal, customerId: String){
        val walletEntity = walletRepository.findByCustomerId(customerId) ?: throw WalletNotFoundException("Cant find the wallet")

        if(walletEntity.balance<amount){
            throw PaymentExceedsBalanceException("Withdraw amount exceeds the balance")
        }
        walletEntity.balance-=amount
        walletRepository.save(walletEntity)
    }
}