package tn.esprit.payment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.payment.R
import tn.esprit.payment.databinding.ItemPaymentBinding
import tn.esprit.payment.models.Payment

class PaymentAdapter(
    private var context: Context,
    private var payments: List<Payment>
) : RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {

    inner class PaymentViewHolder(binding: ItemPaymentBinding) : RecyclerView.ViewHolder(binding.root) {
        val textViewAmount: TextView = binding.textViewAmount
        val textViewDate: TextView = binding.textViewDate
        val textViewMethod: TextView = binding.textViewMethod
        val textViewNumberOfRoommates: TextView = binding.textViewNumberOfRoommates
        val textViewRecurringPayment: TextView = binding.textViewRecurringPayment
        val textViewRecurringPaymentFrequency: TextView = binding.textViewRecurringPaymentFrequency
        val textViewPaymentType: TextView = binding.textViewPaymentType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val binding = ItemPaymentBinding.inflate(LayoutInflater.from(context), parent, false)
        return PaymentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val currentPayment: Payment = payments[position]

        holder.textViewAmount.text = context.getString(R.string.label_amount, currentPayment.amount)
        holder.textViewDate.text = context.getString(R.string.label_date, currentPayment.date)
        holder.textViewMethod.text = context.getString(R.string.label_method, currentPayment.method)
        holder.textViewNumberOfRoommates.text =
            context.getString(R.string.label_number_of_roommates, currentPayment.numberOfRoommates)
        holder.textViewRecurringPayment.text =
            context.getString(R.string.label_recurring_payment, currentPayment.isRecurringPayment.toString())
        holder.textViewRecurringPaymentFrequency.text =
            context.getString(R.string.label_recurring_payment_frequency, currentPayment.recurringPaymentFrequency)
        holder.textViewPaymentType.text =
            context.getString(R.string.label_payment_type, currentPayment.paymentType.joinToString(", "))
    }

    override fun getItemCount(): Int {
        println("Data Size: ${payments.size}")
        return payments.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(payments: List<Payment>) {
        this.payments = payments
        this.notifyDataSetChanged()
    }
}
