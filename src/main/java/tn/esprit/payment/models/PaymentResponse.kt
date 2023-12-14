package tn.esprit.payment.models

data class PaymentResponse(
    val clientSecret: String,
    val payment: Payment
)
