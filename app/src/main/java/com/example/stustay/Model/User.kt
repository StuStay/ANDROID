package com.example.stustay.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User (
    @SerializedName("_id") val _id : String,
    @SerializedName("name") val name : String,
    @SerializedName("last_name") val last_name : String,
    @SerializedName("password") val password : String,
    @SerializedName("email") val email : String,
    @SerializedName("avatar") val avatar : String,
    @SerializedName("age") val age : Int,
    @SerializedName("gender") val gender : String,
    @SerializedName("phone") val phone : String,
    @SerializedName("role") val role : String,
    ): Serializable