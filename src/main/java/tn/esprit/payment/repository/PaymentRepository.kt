package tn.esprit.payment.repository
import com.google.gson.JsonObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// class PaymentRepository {
//
// private val logementService: LogementService
//
// init {
// val retrofit = Retrofit.Builder()
// .baseUrl("http://localhost:3000/")
// .addConverterFactory(GsonConverterFactory.create())
// .build()
//
// logementService = retrofit.create(LogementService::class.java)
// }
//
// suspend fun postPayment(logementId: String, paymentData: PaymentData) {
// val jsonData = JsonObject().apply {
// addProperty("logementId", logementId)
// addProperty("amount", paymentData.amount)
// addProperty("date", paymentData.date)
// // Add other payment properties
// }
//
// logementService.postPayment(logementId, jsonData)
// }
// }