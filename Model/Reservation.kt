package com.example.stustay.Model

import java.util.Date
enum class Feature {
    PARKING,
    DISHWASHER,
    ELEVATOR
}



data class Reservation(
    val _id: String? = null,
    val location:String,
    val checkInDate:Date,
    val checkOutDate:Date,
    val minPrice:String,
    val maxPrice:String,
    val numberOfRoommates:String,
    val bedrooms: Int,
    val features: List<String> = emptyList()
)
