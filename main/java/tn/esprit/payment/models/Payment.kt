package tn.esprit.payment.models

data class Payment(
    val amount: Int,
    val date: String,
    val method: String,
    val numberOfRoommates: Int,
    val isRecurringPayment: Boolean,
    val recurringPaymentFrequency: String,
    val paymentType: List<String>
    )

