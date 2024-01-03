package tn.esprit.payment.models

data class Payment(
    val _id: String,
    val amount: Int,
    val date: String,
    val method: String,
    val numberOfRoommates: Int,
    val paymentType: List<String>
    )

