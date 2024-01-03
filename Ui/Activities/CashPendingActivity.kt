package com.example.stustay.Ui.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.stustay.R

class CashPendingActivity : AppCompatActivity() {
    private lateinit var confirmbtn: Button
    private lateinit var backImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cashpending)

        confirmbtn = findViewById(R.id.closebtn3)
        backImageView = findViewById(R.id.backimg5)

        confirmbtn.setOnClickListener {
            navigateToPaymentList()
        }

        // Set a click listener for the back image
        backImageView.setOnClickListener {
            navigateToPaymentMethod()
        }
    }

    private fun navigateToPaymentList() {
        val intent = Intent(this, PaymentListActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToPaymentMethod() {
        val intent = Intent(this, PaymentMethodActivity::class.java)
        startActivity(intent)
        // You may or may not want to finish() the current activity, depending on your use case
    }
}

