package tn.esprit.payment.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tn.esprit.payment.R
import tn.esprit.payment.models.Payment
import tn.esprit.payment.repository.PaymentRepository
import java.text.SimpleDateFormat
import java.util.*

class HomePayActivity : AppCompatActivity() {

    private lateinit var rentCheckbox: CheckBox
    private lateinit var waterCheckbox: CheckBox
    private lateinit var lightCheckbox: CheckBox
    private lateinit var wifiCheckbox: CheckBox
    private lateinit var confirmButton: Button

    private val paymentRepository = PaymentRepository(PaymentRepository.buildService())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepay)

        rentCheckbox = findViewById(R.id.Rent)
        waterCheckbox = findViewById(R.id.Water)
        lightCheckbox = findViewById(R.id.Light)
        wifiCheckbox = findViewById(R.id.Wifi)
        confirmButton = findViewById(R.id.confirmhomep)

        confirmButton.setOnClickListener {
            handlePaymentConfirmation()
            navigateToPaymentList()

        }
    }

    private fun handlePaymentConfirmation() {
        val selectedPaymentType = mutableListOf<String>()

        if (rentCheckbox.isChecked) {
            selectedPaymentType.add("rent")
        }
        if (waterCheckbox.isChecked) {
            selectedPaymentType.add("water")
        }
        if (lightCheckbox.isChecked) {
            selectedPaymentType.add("light")
        }
        if (wifiCheckbox.isChecked) {
            selectedPaymentType.add("wifi")
        }

        val totalAmount = calculateTotalAmount(selectedPaymentType)

        val paymentData = Payment(
            _id = "",
            amount = totalAmount,
            date = getCurrentDate(),
            method = "Cash",
            numberOfRoommates = 0,
            paymentType = selectedPaymentType
        )

        CoroutineScope(Dispatchers.IO).launch {
            paymentRepository.postPayment(paymentData)
        }

    }

    private fun calculateTotalAmount(selectedPaymentType: List<String>): Int {
        var totalAmount = 0

        for (paymentType in selectedPaymentType) {
            totalAmount += when (paymentType) {
                "rent" -> 300
                "water", "light", "wifi" -> 30
                else -> 0
            }
        }

        return totalAmount
    }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }
    private fun navigateToPaymentList() {
        val intent = Intent(this, PaymentListActivity::class.java)
        startActivity(intent)
        finish()
    }
}
