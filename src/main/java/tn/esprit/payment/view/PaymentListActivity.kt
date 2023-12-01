package tn.esprit.payment.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.DelicateCoroutinesApi
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
        Log.i("payment_page", "this is the payment page")
        val recyclerViewPayments = findViewById<RecyclerView>(R.id.recyclerView)
        paymentAdapter = PaymentAdapter(this@PaymentListActivity, emptyList())
        recyclerViewPayments.adapter = paymentAdapter
        recyclerViewPayments.layoutManager = LinearLayoutManager(this)

        paymentRepository = PaymentRepository(PaymentRepository.buildService())

        loadPayments()

        val closeBtn = findViewById<Button>(R.id.closebtn2)
        closeBtn.setOnClickListener {
            finish()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun loadPayments() {
        GlobalScope.launch {
            try {
                val payments = withContext(Dispatchers.IO) {
                    paymentRepository.getAllPayments()
                }
                Log.i("Received payments:",  "$payments")
                withContext(Dispatchers.Main) {
                    paymentAdapter.setData(payments)
                    println("Data set to adapter. Item count: ${paymentAdapter.itemCount}")
                }
            } catch (e: Exception) {
                Log.e("Error loading payments:", e.toString())
                e.printStackTrace()
            }
        }
    }
}

