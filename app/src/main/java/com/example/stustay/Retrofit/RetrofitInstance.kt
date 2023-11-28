package com.example.stustay.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.esprit.safeguardapplication.Api.LogementApiService

object RetrofitInstance {

    private const val BASE_URL = ""
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val logementApiService: LogementApiService by lazy {
        retrofit.create(LogementApiService::class.java)
    }
}
