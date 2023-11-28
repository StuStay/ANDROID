package com.example.stustay.Model

data class Logement(
    val id: String,
    val titre: String,
    val description: String,
    val nom: String,
    val nombreChambre: Int,
    val prix: Double,
    val contact: String,
    val lieu: String,
    //val categorie: Categorie
)
