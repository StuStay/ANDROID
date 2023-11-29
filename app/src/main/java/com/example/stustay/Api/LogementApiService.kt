package tn.esprit.safeguardapplication.Api

import com.example.stustay.Model.Logement
import com.example.stustay.Model.LogementDetails


import retrofit2.http.Body
import retrofit2.http.GET

import retrofit2.http.POST

import retrofit2.http.Path
import retrofit2.Response
import retrofit2.http.*
interface LogementApiService {

    @GET("/api/logements")
    suspend fun getAllLogements(): Response<List<Logement>>

    @GET("/api/logements/{id}")
    suspend fun getLogementDetails(@Path("id") id: String): Response<LogementDetails>

    @POST("/api/logements")
    suspend fun createLogement(@Body logement: Logement): Response<Logement>

    @PUT("/api/logements/{id}")
    suspend fun updateLogement(@Path("id") id: String, @Body logement: Logement): Response<Logement>

    @DELETE("/api/logements/{id}")
    suspend fun deleteLogement(@Path("id") id: String): Response<Void>

    @GET("/api/logements/search/{lieu}")
    suspend fun searchLogementByLieu(@Path("lieu") lieu: String): Response<Logement>

    @GET("/api/logements/sort")
    suspend fun sortLogementByTitreAsc(): Response<List<Logement>>
}