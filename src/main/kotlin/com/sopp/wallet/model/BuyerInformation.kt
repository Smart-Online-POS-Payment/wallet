package com.sopp.wallet.model

data class BuyerInformation(
    val name: String,
    val surname: String,
    val citizenId: String,
    val phoneNumber: String,
    val email: String,
    val registrationAddress: String,
    val ip: String = "1.1.1.1",
    val buyerId: String,
    val userAgent: String = ""
){
    constructor(verifiedUserModel: VerifiedUserModel): this(name = verifiedUserModel.firstname, surname = verifiedUserModel.surname, citizenId = verifiedUserModel.tc.toString(), phoneNumber = verifiedUserModel.phoneNumber, email = verifiedUserModel.email, registrationAddress = verifiedUserModel.openAddress, buyerId = verifiedUserModel.userId)
}