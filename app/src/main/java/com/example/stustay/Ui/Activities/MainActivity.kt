package com.example.stustay.Ui.Activities

import LogementViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.stustay.R


class MainActivity : AppCompatActivity() {

    private lateinit var logementViewModel: LogementViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logementViewModel = ViewModelProvider(this).get(LogementViewModel::class.java)

    }
}
