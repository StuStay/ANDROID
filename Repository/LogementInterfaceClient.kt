package tn.esprit.safeguardapplication.Api

import android.util.Log
import com.example.stustay.Api.LogementApiService
import com.example.stustay.Model.Logement
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LogementInterfaceClient {
    private val BASE_URL = "http://192.168.1.22:9090/user/"

    private val TAG: String = "CHECK_RESPONSE"

    fun getLogementApiService(): LogementApiService {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return api.create(LogementApiService::class.java)
    }

    fun signIn(email: String, password: String) {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val userApiService = api.create(LogementApiService::class.java)

        val requestBody = RequestBody.create(
            "application/json".toMediaTypeOrNull(),
            "{\"email\":\"$email\", \"password\":\"$password\"}"
        )

        userApiService.createLogement(requestBody).enqueue(object : Callback<Logement> {
            override fun onResponse(call: Call<Logement>, response: Response<Logement>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    user?.let {
                        Log.i(TAG, "onResponse: ${it.nom}")
                    }
                }
            }

            override fun onFailure(call: Call<Logement>, t: Throwable) {
                Log.i(TAG, "onFailure: ${t.message}")
            }
        })
    }
}