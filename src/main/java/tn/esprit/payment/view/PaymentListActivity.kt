package tn.esprit.payment.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tn.esprit.payment.adapter.PaymentAdapter
import tn.esprit.payment.R
import tn.esprit.payment.repository.PaymentRepository

class PaymentListActivity : AppCompatActivity() {

    private lateinit var paymentAdapter: PaymentAdapter
    private lateinit var paymentRepository: PaymentRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.paymentlist)

        val recyclerViewPayments = findViewById<RecyclerView>(R.id.recyclerView)
        paymentAdapter = PaymentAdapter(this@PaymentListActivity, emptyList()) { paymentId ->
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    paymentRepository.deletePayment(paymentId)
                    loadPayments()
                } catch (e: Exception) {
                    Log.e("PaymentListActivity", "Error deleting payment: ${e.message}")
                }
            }
        }

        recyclerViewPayments.adapter = paymentAdapter
        recyclerViewPayments.layoutManager = LinearLayoutManager(this)

        paymentRepository = PaymentRepository(PaymentRepository.buildService())
        loadPayments()

        val closeBtn = findViewById<Button>(R.id.closebtn2)
        closeBtn.setOnClickListener {
            finish()
        }
    }

    private fun loadPayments() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val payments = paymentRepository.getAllPayments()
                withContext(Dispatchers.Main) {
                    paymentAdapter.setData(payments)
                }
            } catch (e: Exception) {
                Log.e("PaymentListActivity", "Error loading payments: ${e.message}")
            }
        }
    }
}
