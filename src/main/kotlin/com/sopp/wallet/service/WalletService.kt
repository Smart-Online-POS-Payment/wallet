package com.sopp.wallet.service

import com.sopp.wallet.entity.WalletEntity
import com.sopp.wallet.exception.PaymentExceedsBalanceException
import com.sopp.wallet.exception.WalletNotFoundException
import com.sopp.wallet.model.WalletModel
import com.sopp.wallet.repository.WalletRepository
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class WalletService(
    private val walletRepository: WalletRepository
) {

    fun getWallet(customerId: String): WalletEntity {
        return walletRepository.findByCustomerId(customerId) ?: throw WalletNotFoundException("Cant find the wallet")
    }

    fun createWallet(walletModel: WalletModel){
        walletRepository.save(WalletEntity(walletModel))
    }

    fun addMoneyToWallet(amount: BigDecimal, customerId: String){
        val walletEntity = walletRepository.findByCustomerId(customerId)
            ?: throw WalletNotFoundException("Cant find the wallet")
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