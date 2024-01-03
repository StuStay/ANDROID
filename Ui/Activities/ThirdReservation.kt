package com.example.stustay.Ui.Activities
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.stustay.Model.Feature
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

class ThirdReservation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_reservation)
        val checkInDate = intent.getStringExtra("CHECK_IN_DATE") ?: "Default Check-In Date"
        val checkOutDate = intent.getStringExtra("CHECK_OUT_DATE") ?: "Default Check-Out Date"
        val numberOfRoommates = intent.getStringExtra("NUMBER_OF_ROOMMATES") ?: "1"
        val location = intent.getStringExtra("LOCATION") ?: "Default Location"

        val editTextMinPrice = findViewById<EditText>(R.id.editTextMinPrice)
        val editTextMaxPrice = findViewById<EditText>(R.id.editTextMinPrice2)
        val radioGroupBedrooms = findViewById<RadioGroup>(R.id.radioGroupBedrooms)
        val checkBoxParking = findViewById<CheckBox>(R.id.checkBoxParking)
        val checkBoxDishwasher = findViewById<CheckBox>(R.id.checkBoxDishwasher)
        val checkBoxElevator = findViewById<CheckBox>(R.id.checkBoxElevator)

        val btnValider = findViewById<Button>(R.id.btnValider)
        val btnBack2: Button = findViewById(R.id.btnBack2)
        btnBack2.setOnClickListener {

            val intent = Intent(this, ListView::class.java)
            startActivity(intent)

        }


        btnValider.setOnClickListener {
            val minPrice = editTextMinPrice.text.toString()
            val maxPrice = editTextMaxPrice.text.toString()
            if (validateInput(minPrice) && validateInput(maxPrice)) {
                val selectedBedrooms: Int = when (radioGroupBedrooms.checkedRadioButtonId) {
                    R.id.select1 -> 1
                    R.id.select2 -> 2
                    R.id.select3 -> 3
                    else -> 1 // Default to 1 bedroom if none selected
                }
                val features = mutableSetOf<Feature>().apply {
                    if (checkBoxParking.isChecked) add(Feature.PARKING)
                    if (checkBoxDishwasher.isChecked) add(Feature.DISHWASHER)
                    if (checkBoxElevator.isChecked) add(Feature.ELEVATOR)
                }

                val newReservation = Reservation (
                    location = location,
                    checkInDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(checkInDate),
                    checkOutDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(checkOutDate),
                    numberOfRoommates = numberOfRoommates,
                    minPrice = minPrice,  // Assuming minPrice is a Double field in Reservation
                    maxPrice = maxPrice,
                    bedrooms = selectedBedrooms,
                    features = features.map { it.name }

                )

                postReservation(newReservation)


            } else {
                // Show an error message for invalid input
                Toast.makeText(this, "Please enter valid prices", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInput(price: String): Boolean {
        return price.isNotEmpty() && price.toDoubleOrNull() != null
    }
    private fun postReservation(reservation: Reservation) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.22:3000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val call = retrofit.postReservation(reservation)

        call.enqueue(object : Callback<Reservation> {
            override fun onResponse(call: Call<Reservation>, response: Response<Reservation>) {
                if (response.isSuccessful) {
                    val postedReservation = response.body()
                    postedReservation?.let {
                        Log.i("PostedReservation", it.toString())
                        showValidationAlert()

                        // Optionally, you can update your local list or perform any other action
                    }
                } else {
                    println("Failed to post reservation. Code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Reservation>, t: Throwable) {
                t.printStackTrace()
                println("Network error")
            }
        })
    }
    private fun showValidationAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Validation Successful")
        builder.setMessage("Your reservation has been successfully validated.")
        builder.setPositiveButton("OK") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }
}
