package tn.esprit.logement.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stustay.Model.Logement
import com.example.stustay.Repository.LogementRepository
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
            // Do something with the logements, like updating LiveData or UI
        }
    }

    fun deleteLogement(logementId: String) {
        viewModelScope.launch {
            repository.deleteLogement(logementId)
        }
    }
}
