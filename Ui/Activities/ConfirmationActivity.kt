package com.example.stustay.Ui.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.stustay.R

class ConfirmationActivity : AppCompatActivity() {
    private lateinit var confirmbtn: Button
    private lateinit var backImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.confirmation)

        confirmbtn = findViewById(R.id.closebtn)
        backImageView = findViewById(R.id.backimg2)

        confirmbtn.setOnClickListener {
            navigateToPaymentList()
        }

        // Set a click listener for the back image
        backImageView.setOnClickListener {
            navigateToCreditCardActivity()
        }
    }

    private fun navigateToPaymentList() {
        val intent = Intent(this, PaymentListActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToCreditCardActivity() {
        val intent = Intent(this, CreditCardActivity::class.java)
        startActivity(intent)
        // You may or may not want to finish() the current activity, depending on your use case
    }
}
