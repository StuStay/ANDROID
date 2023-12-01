package tn.esprit.payment.view
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.payment.R

class CashPendingActivity : AppCompatActivity() {
    private lateinit var confirmbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cashpending)
        confirmbtn = findViewById(R.id.closebtn3)

        confirmbtn.setOnClickListener {
            navigateToPaymentList()
        }
    }

    private fun navigateToPaymentList() {
        val intent = Intent(this, PaymentListActivity::class.java)
        startActivity(intent)
        finish()
    }
}
