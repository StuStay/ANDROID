package tn.esprit.payment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tn.esprit.payment.models.Payment
import tn.esprit.payment.repository.PaymentRepository

class PaymentViewModel(private val repository: PaymentRepository) : ViewModel() {

    fun postPayment(paymentData: Payment) {
        viewModelScope.launch {
            repository.postPayment(paymentData)
        }
    }

    fun getPayments() {
        viewModelScope.launch {
            val payments = repository.getAllPayments()
        }
    }
}
