package tn.esprit.payment.view

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.esprit.payment.R
import tn.esprit.payment.models.Payment
import tn.esprit.payment.repository.PaymentRepository
import tn.esprit.payment.services.Paymentservice
import tn.esprit.payment.viewmodelfactory.PaymentViewModelFactory
import tn.esprit.payment.viewmodels.PaymentViewModel

class PaymentActivity : AppCompatActivity() {

    private lateinit var viewModel: PaymentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val paymentservice: Paymentservice = Retrofit.Builder()
            .baseUrl("http://localhost:3000/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(Paymentservice::class.java)

        val repository = PaymentRepository(paymentservice)

        viewModel = ViewModelProvider(this, PaymentViewModelFactory(repository))
            .get(PaymentViewModel::class.java)

        val confirmButton = findViewById<Button>(R.id.confirmbutton)
        confirmButton.setOnClickListener {
            val paymentData = Payment(
                amount = 100,
                date = "2023-12-01",
                method = "Credit Card",
                numberOfRoommates = 3,
                isRecurringPayment = false,
                recurringPaymentFrequency = "",
                paymentType = listOf("water", "light")
            )

            viewModel.postPayment(paymentData)
        }
    }
}

