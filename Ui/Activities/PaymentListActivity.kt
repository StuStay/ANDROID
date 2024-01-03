package com.example.stustay.Ui.Activities
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stustay.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tn.esprit.payment.adapter.PaymentAdapter
import tn.esprit.payment.repository.PaymentRepository

class PaymentListActivity : AppCompatActivity() {

    private lateinit var paymentAdapter: PaymentAdapter
    private lateinit var paymentRepository: PaymentRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.paymentlist)

        val recyclerViewPayments = findViewById<RecyclerView>(R.id.recyclerView)
        paymentAdapter = PaymentAdapter(this@PaymentListActivity, emptyList()) { paymentId ->
            showDeleteConfirmationDialog(paymentId)
        }

        recyclerViewPayments.adapter = paymentAdapter
        recyclerViewPayments.layoutManager = LinearLayoutManager(this)

        paymentRepository = PaymentRepository(PaymentRepository.buildService())
        loadPayments()

        val closeBtn = findViewById<Button>(R.id.closebtn2)
        closeBtn.setOnClickListener {
            // Start PaymentMethodActivity
            val intent = Intent(this, PaymentMethodActivity::class.java)
            startActivity(intent)

            finish()
        }
        val imageViewLogo3 = findViewById<ImageView>(R.id.imageViewLogo3)
        imageViewLogo3.setOnClickListener {
            loadPayments()
        }
    }


    @OptIn(DelicateCoroutinesApi::class)
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

    private fun showDeleteConfirmationDialog(paymentId: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Payment")
        builder.setMessage("Are you sure you want to delete this payment?")
        builder.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
            deletePayment(paymentId)
        }
        builder.setNegativeButton("No") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun deletePayment(paymentId: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                paymentRepository.deletePayment(paymentId)
                loadPayments()
            } catch (e: Exception) {
                Log.e("PaymentListActivity", "Error deleting payment: ${e.message}")
            }
        }
    }
}