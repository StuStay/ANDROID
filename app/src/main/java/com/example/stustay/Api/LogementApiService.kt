package tn.esprit.safeguardapplication.Api

import com.example.stustay.Model.Logement
import retrofit2.http.GET
import retrofit2.http.Path

interface LogementApiService {

    @GET("/api/logements")
    suspend fun getAllLogements(): List<Logement>

    @GET("/api/logements/{id}")
    suspend fun getLogementById(@Path("id") logementId: String): Logement
}