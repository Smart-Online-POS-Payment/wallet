package com.sopp.wallet.service

import com.sopp.wallet.entity.WalletEntity
import com.sopp.wallet.exception.NegativeAmountException
import com.sopp.wallet.exception.PaymentExceedsBalanceException
import com.sopp.wallet.exception.WalletNotFoundException
import com.sopp.wallet.model.CardModel
import com.sopp.wallet.model.TransactionResponse
import com.sopp.wallet.repository.WalletRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class WalletService(
    private val walletRepository: WalletRepository,
    private val authService: AuthService,
    private val istepayService: IstepayService,
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun getWallet(customerId: String): WalletEntity {
        return walletRepository.findByCustomerId(customerId) ?: createWallet(customerId)
    }

    fun createWallet(customerId: String): WalletEntity {
        return walletRepository.save(WalletEntity(customerId = customerId, balance = BigDecimal.ZERO))
    }

    fun addMoneyToWallet(
        amount: BigDecimal,
        customerId: String,
    ) {
        if (amount <= BigDecimal.ZERO) throw NegativeAmountException()

        val walletEntity =
            walletRepository.findByCustomerId(customerId)
                ?: throw WalletNotFoundException("Can't find the wallet")
        walletEntity.balance += amount
        walletRepository.save(walletEntity)
    }

    suspend fun depositMoneyToWallet(
        amount: BigDecimal,
        customerId: String,
        cardModel: CardModel,
    ) {
        if (amount <= BigDecimal.ZERO) throw NegativeAmountException()
        try {
            logger.info("Starting to deposit money to wallet...")
            val user = authService.getUser(customerId)
            logger.info("Got user:$user")
            val transactionResponse = istepayService.depositMoney(cardModel, user, amount).transaction
            logger.info("Response:$transactionResponse")
            if (transactionResponse.status == TransactionResponse.Status.Succeeded || transactionResponse.status == TransactionResponse.Status.Processing) {
                val walletEntity = walletRepository.findByCustomerId(customerId)

                if(walletEntity == null){
                    val newWallet = createWallet(customerId)
                    newWallet.balance += amount
                    walletRepository.save(newWallet)
                }
                else{
                    walletEntity.balance += amount
                    walletRepository.save(walletEntity)
                }
            }
        } catch (e: Exception) {
            println("Exception${e.message}")
        }
    }

    fun withdrawMoney(
        amount: BigDecimal,
        customerId: String,
    ) {
        val walletEntity = walletRepository.findByCustomerId(customerId) ?: throw WalletNotFoundException("Cant find the wallet")

        if (walletEntity.balance < amount) {
            throw PaymentExceedsBalanceException("Withdraw amount exceeds the balance")
        }
        walletEntity.balance -= amount
        walletRepository.save(walletEntity)
    }
}
