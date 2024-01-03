package com.example.stustay.Ui.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stustay.Model.Reservation
import com.example.stustay.R
import com.example.stustay.Retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Locale

class ResevationAdapter(private val context: Context, var reservations: List<Reservation>) :
    RecyclerView.Adapter<ResevationAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val location: TextView = itemView.findViewById(R.id.location)
        val numberOfRoommates: TextView = itemView.findViewById(R.id.numberOfRoommates)
        val checkInDate: TextView = itemView.findViewById(R.id.checkInDate)
        val checkOutDate: TextView = itemView.findViewById(R.id.checkOutDate)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton) // Add this line
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reservations.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val checkInDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val checkOutDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        holder.location.text =reservations[position].location
        holder.numberOfRoommates.text = reservations[position].numberOfRoommates
        holder.checkInDate.text = checkInDateFormat.format(reservations[position].checkInDate)
        holder.checkOutDate.text = checkOutDateFormat.format(reservations[position].checkOutDate)
        holder.deleteButton.setOnClickListener {

            deleteReservation(position)
        }


    }
    fun deleteReservation(position: Int) {
        val reservationId = reservations[position]._id // Assuming '_id' is the database identifier

        if (reservationId != null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.22:3000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)

            val call = retrofit.deleteReservation(reservationId)

            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // Remove the item from the local list and notify the adapter
                        val updatedList = reservations.toMutableList()
                        updatedList.removeAt(position)
                        reservations = updatedList.toList()
                        notifyItemRemoved(position)
                    } else {
                        println("Failed to delete reservation. Code: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    t.printStackTrace()
                    println("Network error")
                }
            })
        } else {
            // Handle the case when reservationId is null
            println("ReservationId is null. Cannot delete reservation.")
        }
    }

}
