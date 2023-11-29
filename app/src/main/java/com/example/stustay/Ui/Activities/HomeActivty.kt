package com.example.stustay.Ui.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.stustay.R

class HomeActivty : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun onOrderNowClick(view: View) {

        val buttonId = view.id


        val intent = Intent(this, DetailsActivity::class.java)


        intent.putExtra("buttonId", buttonId)


        startActivity(intent)
    }
}
