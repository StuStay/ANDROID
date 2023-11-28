/*package com.example.stustay.Ui.Activities
import com.example.stustay.Retrofit.RetrofitInstance
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stustay.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val LogementApiService = RetrofitInstance.logementApiService

        val call = LogementApiService.getAllLogements()
        call.enqueue(object : Callback<List<Logement>> {
            override fun onResponse(call: Call<List<Logement>>, response: Response<List<Logement>>) {
                if (response.isSuccessful) {
                    val logements = response.body()
                }
            }

            override fun onFailure(call: Call<List<Logement>>, t: Throwable) {
            }
        })

        val logementId = "ID_DU_LOGEMENT"
        val detailsCall = LogementApiService.getLogementDetails(logementId)
        detailsCall.enqueue(object : Callback<Logement> {
            override fun onResponse(call: Call<Logement>, response: Response<Logement>) {
                if (response.isSuccessful) {
                    val logement = response.body()
                }
            }

            override fun onFailure(call: Call<Logement>, t: Throwable) {
            }
        })
    }

}*/