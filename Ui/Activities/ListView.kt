package com.example.stustay.Ui.Activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stustay.Model.Reservation
import com.example.stustay.R
import com.example.stustay.Retrofit.ApiInterface
import com.example.stustay.Ui.Adapters.ResevationAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ListView : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var reservationAdapter: ResevationAdapter
    private lateinit var reservationList: List<Reservation>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)



            recyclerView = findViewById(R.id.rvMain)
            reservationList = emptyList()
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            getReservations()


    }


    private fun getReservations() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.22:3000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val call = retrofit.getReservations()

        call.enqueue(object : Callback<List<Reservation>> {
            override fun onResponse(call: Call<List<Reservation>>, response: Response<List<Reservation>>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.let {
                        Log.i("reservationList ",it.toString())
                        reservationList=it
                        recyclerView.adapter=ResevationAdapter(applicationContext,reservationList)



                    }
                } else {

                    println("Failed to fetch reservations. Code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Reservation>>, t: Throwable) {
                t.printStackTrace()
                println("Network error")
            }
        })
    }
}