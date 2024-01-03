package com.example.stustay.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stustay.Model.Logement
import com.example.stustay.Repository.LogementRepository

class DetailsViewModel(private val logementRepository: LogementRepository) : ViewModel() {
    companion object {
        private const val TAG = "DetailsViewModel"
    }
    suspend fun getAllLogements(): LiveData<Logement?> {
        return logementRepository.getAllLogements()
    }
    class Factory(private val logementRepository: LogementRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PostActivityViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PostActivityViewModel(logementRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}