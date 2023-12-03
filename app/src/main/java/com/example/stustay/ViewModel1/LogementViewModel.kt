package tn.esprit.logement.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stustay.Model1.Logement
import com.example.stustay.Repository1.LogementRepository
import kotlinx.coroutines.launch


class LogementViewModel(private val repository: LogementRepository) : ViewModel() {

    fun createLogement(logementData: Logement) {
        viewModelScope.launch {
            repository.createLogement(logementData)
        }
    }

    fun getAllLogements() {
        viewModelScope.launch {
            val logements = repository.getAllLogements()

        }
    }

    fun deleteLogement(logementId: String) {
        viewModelScope.launch {
            repository.deleteLogement(logementId)
        }
    }
}
