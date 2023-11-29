package com.example.stustay.Repository




import com.example.stustay.Model.Logement
import com.example.stustay.Model.LogementDetails
import retrofit2.Response
import tn.esprit.safeguardapplication.Api.LogementApiService



class LogementRepository(private val logementApiService: LogementApiService) {

    suspend fun getAllLogements(): Response<List<Logement>> = logementApiService.getAllLogements()

    suspend fun getLogementDetails(id: String): Response<LogementDetails> = logementApiService.getLogementDetails(id)

    suspend fun createLogement(logement: Logement): Response<Logement> = logementApiService.createLogement(logement)

    suspend fun updateLogement(id: String, logement: Logement): Response<Logement> = logementApiService.updateLogement(id, logement)

    suspend fun deleteLogement(id: String): Response<Void> = logementApiService.deleteLogement(id)

    suspend fun searchLogementByLieu(lieu: String): Response<Logement> = logementApiService.searchLogementByLieu(lieu)

    suspend fun sortLogementByTitreAsc(): Response<List<Logement>> = logementApiService.sortLogementByTitreAsc()
}
