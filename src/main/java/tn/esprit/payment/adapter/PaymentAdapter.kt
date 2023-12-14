package tn.esprit.payment.adapter
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.payment.R
import tn.esprit.payment.databinding.ItemPaymentBinding
import tn.esprit.payment.models.Payment
import java.text.SimpleDateFormat
import java.util.Locale

class PaymentAdapter(
    private var context: Context,
    private var payments: List<Payment>,
    private val onDeleteClickListener: (String) -> Unit
) : RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {

    inner class PaymentViewHolder(binding: ItemPaymentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val textViewAmount: TextView = binding.textViewAmount
        val textViewDate: TextView = binding.textViewDate
        val textViewMethod: TextView = binding.textViewMethod
        val textViewNumberOfRoommates: TextView = binding.textViewNumberOfRoommates
        val textViewPaymentType: TextView = binding.textViewPaymentType
        val buttonDelete: Button = binding.button3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val binding = ItemPaymentBinding.inflate(LayoutInflater.from(context), parent, false)
        return PaymentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val currentPayment: Payment = payments[position]

        holder.textViewAmount.text = context.getString(R.string.label_amount, currentPayment.amount)
        holder.textViewDate.text = formatDate(currentPayment.date)
        holder.textViewMethod.text = context.getString(R.string.label_method, currentPayment.method)
        holder.textViewNumberOfRoommates.text =
            context.getString(R.string.label_number_of_roommates, currentPayment.numberOfRoommates)
        holder.textViewPaymentType.text =
            context.getString(
                R.string.label_payment_type,
                currentPayment.paymentType.joinToString(", ")
            )

        holder.buttonDelete.setOnClickListener {
            currentPayment._id?.let { it1 -> onDeleteClickListener.invoke(it1) } // Using _id as a String
        }
    }

    private fun formatDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val date = inputFormat.parse(dateString)
        val formattedDate = outputFormat.format(date!!)

        return "Date: $formattedDate"
    }

    override fun getItemCount(): Int {
        return payments.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(payments: List<Payment>) {
        this.payments = payments
        notifyDataSetChanged()
    }
}

