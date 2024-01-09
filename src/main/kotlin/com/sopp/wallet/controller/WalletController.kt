package com.sopp.wallet.controller

import com.sopp.wallet.entity.WalletEntity
import com.sopp.wallet.exception.PaymentExceedsBalanceException
import com.sopp.wallet.exception.WalletNotFoundException
import com.sopp.wallet.model.CardModel
import com.sopp.wallet.model.ResponseModel
import com.sopp.wallet.service.CardService
import com.sopp.wallet.service.WalletService
import org.springframework.http.HttpStatusCode
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal

@RestController
@RequestMapping("wallet")
class WalletController(
    private val walletService: WalletService,
    private val cardService: CardService,
) {
    @GetMapping("/{customerId}")
    fun getWallet(
        @PathVariable customerId: String,
    ): WalletEntity {
        try {
            return walletService.getWallet(customerId)
        } catch (ex: WalletNotFoundException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(404), ex.localizedMessage, ex)
        }
    }

    @PostMapping("/{customerId}")
    fun createWallet(
        @PathVariable customerId: String,
    ): WalletEntity {
        return walletService.createWallet(customerId)
    }

    @PutMapping("/{customerId}/amount/{amount}")
    fun addMoneyToWallet(
        @PathVariable customerId: String,
        @PathVariable amount: BigDecimal,
    ): ResponseModel {
        return try {
            walletService.addMoneyToWallet(amount, customerId)
            ResponseModel("200", "Success")
        } catch (ex: WalletNotFoundException) {
            ResponseModel("404", "Wallet not found")
        }
    }

    @PutMapping("card-payment/{customerId}/amount/{amount}")
    suspend fun addMoneyToWalletFromCard(
        @PathVariable customerId: String,
        @PathVariable amount: BigDecimal,
        @RequestBody cardModel: CardModel,
    ): ResponseModel {
        return try {
            walletService.depositMoneyToWallet(amount, customerId, cardModel)
            ResponseModel("200", "Success")
        } catch (ex: Exception) {
            ResponseModel("500", ex.message.toString())
        }
    }

    @DeleteMapping("/{customerId}/amount/{amount}")
    fun withdrawMoney(
        @PathVariable customerId: String,
        @PathVariable amount: BigDecimal,
    ): ResponseModel {
        return try {
            walletService.withdrawMoney(amount, customerId)
            ResponseModel("200", "Success")
        } catch (ex: WalletNotFoundException) {
            ResponseModel("404", "Wallet not found")
        } catch (ex: PaymentExceedsBalanceException) {
            ResponseModel("500", "Payment exceeds balance")
        }
    }

    @GetMapping("/{customerId}/cards")
    fun getCards(
        @PathVariable customerId: String,
    ): List<CardModel> {
        return cardService.getCards(customerId)
    }

    @PostMapping("/{customerId}/cards")
    fun saveCard(
        @RequestBody cardModel: CardModel,
        @PathVariable customerId: String,
    ) {
        cardService.saveCard(customerId, cardModel)
    }
}
