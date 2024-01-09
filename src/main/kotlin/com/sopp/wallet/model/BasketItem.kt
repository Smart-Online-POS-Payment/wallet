package com.sopp.wallet.model

import java.math.BigDecimal

data class BasketItem(
    val id: String,
    val name: String,
    val category: String,
    val subCategory: String? = null,
    val itemType: String,
    val price: BigDecimal,
    val productType: ProductType
) {
    enum class ProductType {
        Physical,
        Virtual
    }
}