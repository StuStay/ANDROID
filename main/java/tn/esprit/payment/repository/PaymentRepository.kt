package tn.esprit.payment.repository
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.esprit.payment.models.Payment
import tn.esprit.payment.services.Paymentservice

class PaymentRepository(private var paymentservice: Paymentservice) {

 init {
  val retrofit = Retrofit.Builder()
   .baseUrl("http://192.168.1.6:3000/")
   .addConverterFactory(GsonConverterFactory.create())
   .build()

  paymentservice = retrofit.create(Paymentservice::class.java)
 }

 suspend fun postPayment(paymentData: Payment) {
  val jsonData = JsonObject().apply {
   addProperty("amount", paymentData.amount)
   addProperty("date", paymentData.date)
   addProperty("method", paymentData.method)
   addProperty("numberOfRoommates", paymentData.numberOfRoommates)
   addProperty("isRecurringPayment", paymentData.isRecurringPayment)
   addProperty("recurringPaymentFrequency", paymentData.recurringPaymentFrequency)
   add("paymentType", Gson().toJsonTree(paymentData.paymentType))
  }

  paymentservice.postPayment(jsonData)
 }
 suspend fun getAllPayments(): List<Payment> {
  return paymentservice.getAllPayments()
 }
  suspend fun deletePayment(paymentId: String) {
   paymentservice.deletePayment(paymentId)
  }
 }
