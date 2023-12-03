package com.example.stustay.Api

import com.example.stustay.Model1.Logement
import com.example.stustay.Model1.LogementDetails
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

import retrofit2.http.*
interface LogementApiService {

    @GET("/api/logements")
    suspend fun getAllLogements(): List<Logement>

    @GET("/api/logements/{id}")
    suspend fun getLogementDetails(@Path("id") id: String): Response<LogementDetails>

    @POST("/api/logements")
    suspend fun createLogement(@Body jsonData: JsonObject)

    @PUT("/api/logements/{id}")
    suspend fun updateLogement(@Path("id") id: String, @Body logement: Logement): Response<Logement>

    @DELETE("/api/logements/{id}")
    suspend fun deleteLogement(@Path("id") id: String)

    @GET("/api/logements/search/{lieu}")
    suspend fun searchLogementByLieu(@Path("lieu") lieu: String): List<Logement>

    @GET("/api/logements/sort")
    suspend fun sortLogementByTitreAsc(): List<Logement>
}