package com.example.stustay.Ui.Activities

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.ImageButton
import android.widget.TextView
import com.example.stustay.R
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SecondeReservation : AppCompatActivity() {
    private lateinit var editTextDate: TextInputEditText
    private lateinit var editTextDate2: TextInputEditText
    private lateinit var textNumberOfRoommates: TextView

    private var numberOfRoommates: Int = 1 // Initial number of roommates

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seconde_reservation)
        val location = intent.getStringExtra("location") ?: "Default Location"

        editTextDate = findViewById(R.id.editTextDate)
        editTextDate2 = findViewById(R.id.editTextDate2)
        textNumberOfRoommates = findViewById(R.id.textNumberOfRoommates)

        editTextDate.setOnClickListener {
            showDatePickerDialog(editTextDate)
        }
        editTextDate2.setOnClickListener {
            showDatePickerDialog(editTextDate2)
        }
        val btnIncrement: ImageButton = findViewById(R.id.btnIncrement)
        btnIncrement.setOnClickListener {
            incrementNumberOfRoommates()
        }

        val btnDecrement: ImageButton = findViewById(R.id.btnDecrement)
        btnDecrement.setOnClickListener {
            decrementNumberOfRoommates()
        }

        val btnSuivant: Button = findViewById(R.id.btnSuivant1)
        btnSuivant.setOnClickListener {
            val checkInDate = editTextDate.text.toString()
            val checkOutDate = editTextDate2.text.toString()
            val numberOfRoommatesText = textNumberOfRoommates.text.toString()
            val intent = Intent(this, ThirdReservation::class.java)

            // Put the data as extras in the Intent
            intent.putExtra("CHECK_IN_DATE", checkInDate)
            intent.putExtra("CHECK_OUT_DATE", checkOutDate)
            intent.putExtra("NUMBER_OF_ROOMMATES", numberOfRoommatesText)
            intent.putExtra("LOCATION", location)

            // Start the ThirdReservation activity with the Intent
            startActivity(intent)



        }

    }

private fun showDatePickerDialog(editText: TextInputEditText) {
        val calendar = Calendar.getInstance()

        val datePickerDialog = DatePickerDialog(
            this,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                // Update the TextInputEditText with the selected date
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)

                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                editText.setText(dateFormat.format(selectedDate.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

private fun incrementNumberOfRoommates() {
    numberOfRoommates++
    updateNumberOfRoommates()
}

private fun decrementNumberOfRoommates() {
    if (numberOfRoommates > 1) {
        numberOfRoommates--
        updateNumberOfRoommates()
    }
}

private fun updateNumberOfRoommates() {
    textNumberOfRoommates.text = numberOfRoommates.toString()
}
    fun onBackButtonClick(view: View) {
        // Finish the current activity to go back to MainActivity
        finish()
    }
}

