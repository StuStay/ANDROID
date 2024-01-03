package com.example.stustay.Repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.stustay.Api.LogementApiService

object RetrofitInstance {
    val api: LogementApiService by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        Retrofit.Builder()
            .baseUrl("http://192.168.1.22:3000/logements/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LogementApiService::class.java)
    }

}