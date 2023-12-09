package com.example.stustay.ViewModel

import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.stustay.Repository.LogementRepository
import kotlinx.coroutines.Dispatchers

class PostActivityViewModel(private val logementRepository: LogementRepository) : ViewModel() {


    companion object {
        private const val TAG = "PostActivityViewModel"
    }

    fun createLogement(
        images: String,
        titre: String,
        description: String,
        nom: String,
        nombreChambre: Int,
        prix: Double,
        contact: String,
        lieu: String
    ) = liveData(Dispatchers.IO) {
        try {

            val response = logementRepository.createLogement(
                images,
                titre,
                description,
                nom,
                nombreChambre,
                prix,
                contact,
                lieu
            )


            if (response != null) {
                emit(response)
            } else {
                Log.e(TAG, "Unsuccessful response or null response")
                emit(null)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Exception: ${e.message}")
            emit(null)
        }
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
