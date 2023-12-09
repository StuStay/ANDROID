package com.example.stustay.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stustay.Model.Logement

interface LogementRepository {
    suspend fun createLogement(images: String?, titre: String,description:String,nom:String,nombreChambre:Int,prix:Double,contact:String,lieu:String): LiveData<Logement?>
    suspend fun getAllLogements(): LiveData<Logement?>

}