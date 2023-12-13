package com.example.stustay.Repository

import com.example.stustay.Model.User
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RestApi {

    @POST("/user/loginAndroid")
    fun login(@Body info: LoginRequest): Call<JsonObject>
    @POST("/user/addAndroid")
    fun register(@Body info: RegisterRequest): Call<JsonObject>
    @POST("/user/updateProfileAndroid")
    fun updateProfil(@Body info: UpdateRequest): Call<JsonObject>


    companion object {

        var BASE_URL = "http://172.22.160.1:3000"

        fun create(): RestApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RestApi::class.java)
        }
    }
    data class LoginRequest(
        val email: String,
        val password: String
    )
    data class UpdateRequest(
        val id : String,
        val name: String,
        val last_name: String,
        val email: String,
        val password: String,
        val age : Int,
        val gender : String,
        val phone : String,
        val role : String
    )
    data class RegisterRequest(
        val name: String,
        val last_name: String,
        val email: String,
        val password: String,
        val age : Int,
        val gender : String,
        val phone : String,
        val role : String
    )
}