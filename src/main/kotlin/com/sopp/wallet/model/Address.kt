package com.sopp.wallet.model

data class Address(
    val contactName: String,
    val city: String,
    val country: String,
    val openAddress: String,
    val phoneNumber: String
){
    constructor(verifiedUserModel: VerifiedUserModel): this(contactName = verifiedUserModel.firstname + " " + verifiedUserModel.surname,
        city = verifiedUserModel.city, country = verifiedUserModel.country, openAddress = verifiedUserModel.openAddress, phoneNumber = verifiedUserModel.phoneNumber)
}