package com.example.stustay.Ui.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.stustay.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tn.esprit.payment.models.Payment
import tn.esprit.payment.repository.PaymentRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CreditCardActivity : AppCompatActivity() {

    private lateinit var editCardNumber: EditText
    private lateinit var editCvv: EditText
    private lateinit var editExpiryDate: EditText
    private lateinit var btnSubmit: Button
    private lateinit var backImageView: ImageView

    private val paymentRepository = PaymentRepository(PaymentRepository.buildService())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.credit_cardl)

        editCardNumber = findViewById(R.id.editcardtext)
        editCvv = findViewById(R.id.editcvvtext)
        editExpiryDate = findViewById(R.id.editdatetext)
        btnSubmit = findViewById(R.id.confirmbutton)
        backImageView = findViewById(R.id.backimg)

        btnSubmit.setOnClickListener {
            val cardNumber = editCardNumber.text.toString()
            val cvv = editCvv.text.toString()
            val expiryDate = editExpiryDate.text.toString()

            handleCreditCardInfo(cardNumber, cvv, expiryDate)
        }

        backImageView.setOnClickListener {
            navigateBackToPaymentMethod()
        }
    }

    private fun handleCreditCardInfo(cardNumber: String, cvv: String, expiryDate: String) {
        if (validateInput(cardNumber, cvv, expiryDate)) {
            val paymentData = Payment(
                _id = "",
                amount = 300,
                date = getCurrentDate(),
                method = "credit card",
                numberOfRoommates = 2,
                paymentType = listOf("rent")
            )

            CoroutineScope(Dispatchers.IO).launch {
                paymentRepository.postPayment(paymentData)

                navigateToPaymentList()
            }
        } else {
            showValidationFailureAlert("Invalid input", "Please enter valid information for all fields.")
        }
    }

    private fun showValidationFailureAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun validateInput(cardNumber: String, cvv: String, expiryDate: String): Boolean {
        val cardNumberValid = cardNumber.matches(Regex("\\d{16}"))
        val cvvValid = cvv.matches(Regex("\\d{3}"))
        val expiryDateValid = validateDateFormat(expiryDate)

        if (!cardNumberValid) {
            editCardNumber.error = "Invalid card number"
        }
        if (!cvvValid) {
            editCvv.error = "Invalid CVV"
        }
        if (!expiryDateValid) {
            editExpiryDate.error = "Invalid date format (MM/DD)"
        }

        return cardNumberValid && cvvValid && expiryDateValid
    }

    private fun validateDateFormat(date: String): Boolean {
        // Assuming MM/DD format
        val dateFormatRegex = Regex("^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])$")
        return date.matches(dateFormatRegex)
    }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }

    private fun navigateBackToPaymentMethod() {
        val intent = Intent(this, PaymentMethodActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToPaymentList() {
        val intent = Intent(this, PaymentListActivity::class.java)
        startActivity(intent)
        finish()
    }
}
