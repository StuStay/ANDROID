
package tn.esprit.payment.view
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tn.esprit.payment.R
import tn.esprit.payment.models.Payment
import tn.esprit.payment.repository.PaymentRepository

class CreditCardActivity : AppCompatActivity() {

private lateinit var editCardNumber: EditText
private lateinit var editCvv: EditText
private lateinit var editExpiryDate: EditText
private lateinit var btnSubmit: Button

//private val paymentRepository = PaymentRepository(PaymentRepository.buildService())

override fun onCreate(savedInstanceState: Bundle?) {
super.onCreate(savedInstanceState)
setContentView(R.layout.credit_cardl)

editCardNumber = findViewById(R.id.editcardtext)
editCvv = findViewById(R.id.editcvvtext)
editExpiryDate = findViewById(R.id.editdatetext)
btnSubmit = findViewById(R.id.confirmbutton)

btnSubmit.setOnClickListener {
val cardNumber = editCardNumber.text.toString()
val cvv = editCvv.text.toString()
val expiryDate = editExpiryDate.text.toString()

handleCreditCardInfo(cardNumber, cvv, expiryDate)
}
}

private fun handleCreditCardInfo(cardNumber: String, cvv: String, expiryDate: String) {
val paymentData = Payment(
amount = 300,
date = "2023-12-01",
method = "credit card",
numberOfRoommates = 0,
isRecurringPayment = false,
recurringPaymentFrequency = "Monthly",
paymentType = listOf("water")
)

CoroutineScope(Dispatchers.IO).launch {
//paymentRepository.postPayment(paymentData)

navigateToPaymentList()
}
}

private fun navigateToPaymentList() {
val intent = Intent(this, PaymentListActivity::class.java)
startActivity(intent)
finish()
}
}
