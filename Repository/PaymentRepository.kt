package tn.esprit.payment.repository
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.esprit.payment.models.Payment
import tn.esprit.payment.services.Paymentservice

class PaymentRepository(private var paymentservice: Paymentservice) {

 suspend fun postPayment(paymentData: Payment) {
  try {
   val jsonData = JsonObject().apply {
    addProperty("amount", paymentData.amount)
    addProperty("date", paymentData.date)
    addProperty("method", paymentData.method)
    addProperty("numberOfRoommates", paymentData.numberOfRoommates)
    add("paymentType", Gson().toJsonTree(paymentData.paymentType))
   }

   paymentservice.postPayment(jsonData)
  } catch (e: Exception) {
   Log.e("PaymentRepository", "Error posting payment: ${e.message}")
   throw e
  }
 }


 suspend fun getAllPayments(): MutableList<Payment> {
  return paymentservice.getAllPayments().toMutableList()
 }

 suspend fun deletePayment(paymentId: String) {
  try {
   paymentservice.deletePayment(paymentId)
  } catch (e: Exception) {
   Log.e("PaymentRepository", "Error deleting payment: ${e.message}")
   throw e
  }
 }



 companion object {
  private const val BASE_URL = "http://192.168.1.22:3000/api/"

  fun buildService(): Paymentservice {
   val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

   return retrofit.create(Paymentservice::class.java)
  }
 }
}

