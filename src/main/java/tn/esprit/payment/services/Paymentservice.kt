package tn.esprit.payment.services

import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import tn.esprit.payment.models.Payment

interface Paymentservice {
    @POST("api/payments")
    suspend fun postPayment(@Body jsonData: JsonObject)

    @DELETE("api/payments/{paymentId}")
    suspend fun deletePayment(@Path("paymentId") paymentId: String)


    @GET("api/payments")
    suspend fun getAllPayments(): List<Payment>
}




