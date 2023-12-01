package tn.esprit.payment.view
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.payment.R

class PaymentMethodActivity : AppCompatActivity() {
    private lateinit var cashbtn: Button
    private lateinit var cardbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.paymentmethod)
        cashbtn = findViewById(R.id.buttonCashPayment)
        cardbtn = findViewById(R.id.buttonCreditCardPayment)
        cashbtn.setOnClickListener {
            navigateToCashPending()
        }
        cardbtn.setOnClickListener {
            navigateToCreditCard()
        }

    }

    private fun navigateToCashPending() {
        val intent = Intent(this, CashPendingActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToCreditCard() {
        val intent = Intent(this, CreditCardActivity::class.java)
        startActivity(intent)
        finish()
    }
}