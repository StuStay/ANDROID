package com.example.stustay.Api

import retrofit2.Call
import com.example.stustay.Model.Logement
import com.example.stustay.Model.LogementDetails

import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

import retrofit2.http.*
interface LogementApiService {

    @GET("logements")
    fun getAllLogements(): Response<Logement>

    @GET("logements/{id}")
    fun getLogementDetails(@Path("id") id: String): Response<LogementDetails>

    @POST("logements")
    fun createLogement(@Body requestBody: RequestBody): Call<Logement>

    @PUT("logements/{id}")
    fun updateLogement(@Path("id") id: String, @Body logement: Logement): Response<Logement>

    @DELETE("logements/{id}")
    fun deleteLogement(@Path("id") id: String)

    @GET("logements/search/{lieu}")
    fun searchLogementByLieu(@Path("lieu") lieu: String): List<Logement>

    @GET("logements/sort")
    fun sortLogementByTitreAsc(): List<Logement>
}