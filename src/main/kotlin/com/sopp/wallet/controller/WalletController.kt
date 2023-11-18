package com.sopp.wallet.controller

import com.sopp.wallet.entity.WalletEntity
import com.sopp.wallet.exception.PaymentExceedsBalanceException
import com.sopp.wallet.exception.WalletNotFoundException
import com.sopp.wallet.model.ResponseModel
import com.sopp.wallet.model.WalletModel
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
    private val walletService: WalletService
) {

    @GetMapping("/{customerId}")
    fun getWallet(@PathVariable customerId: String): WalletEntity {
        try {
            return walletService.getWallet(customerId)
        }
        catch (ex: WalletNotFoundException){
            throw ResponseStatusException(HttpStatusCode.valueOf(404), ex.localizedMessage, ex)
        }
    }

    @PostMapping
    fun createWallet(@RequestBody walletModel: WalletModel){
        walletService.createWallet(walletModel)
    }

    @PutMapping("/{customerId}/amount/{amount}")
    fun addMoneyToWallet(@PathVariable customerId: String, @PathVariable amount: BigDecimal){
        try {
            walletService.addMoneyToWallet(amount, customerId)
        }
        catch (ex: WalletNotFoundException){
            throw ResponseStatusException(HttpStatusCode.valueOf(404), ex.localizedMessage, ex)
        }
    }

    @DeleteMapping("/{customerId}/amount/{amount}")
    fun withdrawMoney(@PathVariable customerId: String, @PathVariable amount: BigDecimal,): ResponseModel {
        return try {
            walletService.withdrawMoney(amount, customerId)
            ResponseModel("200", "Success")

        } catch (ex: WalletNotFoundException){
            ResponseModel("404","Wallet not found")
        } catch (ex: PaymentExceedsBalanceException){
            ResponseModel("500","Payment exceeds balance")
        }
    }
}