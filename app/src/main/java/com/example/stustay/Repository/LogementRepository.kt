package com.example.stustay.Repository

import com.example.stustay.Model.Logement
import tn.esprit.safeguardapplication.Api.LogementApiService


class LogementRepository(private val logementApiService: LogementApiService) {

    suspend fun getAllLogements(): List<Logement> {
        return logementApiService.getAllLogements()
    }

    suspend fun getLogementById(logementId: String): Logement {
        return logementApiService.getLogementById(logementId)
    }

}
