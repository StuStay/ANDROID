package com.example.stustay.Retrofit

import com.example.stustay.Model.Reservation
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @GET("reservations")
    fun getReservations(): Call<List<Reservation>>
    @POST("reservations")
    fun postReservation(@Body reservation: Reservation): Call<Reservation>
    @DELETE("reservations/{id}")
    fun deleteReservation(@Path("id") id: String): Call<Void>
}
