package com.example.stustay.Repository

import com.example.stustay.Api.LogementApiService
import com.example.stustay.Model.Logement
import com.google.gson.JsonObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LogementRepository(private var logementApiService: LogementApiService) {

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://your_base_url_here/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        logementApiService = retrofit.create(LogementApiService::class.java)
    }

    suspend fun createLogement(logementData: Logement) {
        val jsonData = JsonObject().apply {
            addProperty("images",logementData.images)
            addProperty("titre", logementData.titre)
            addProperty("description", logementData.description)
            addProperty("nom", logementData.nom)
            addProperty("nombreChambre", logementData.nombreChambre)
            addProperty("prix", logementData.prix)
            addProperty("contact", logementData.contact)
            addProperty("lieu", logementData.lieu)
        }

        logementApiService.createLogement(jsonData)
    }

    suspend fun getAllLogements(): List<Logement> {
        return logementApiService.getAllLogements()
    }

    suspend fun deleteLogement(logementId: String) {
        logementApiService.deleteLogement(logementId)
    }
}