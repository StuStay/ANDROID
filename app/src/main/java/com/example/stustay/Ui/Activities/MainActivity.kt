package com.example.stustay.Ui.Activities

import LogementAdapter
import LogementViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stustay.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: LogementViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var logementAdapter: LogementAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(LogementViewModel::class.java)

        recyclerView = findViewById(R.id.recyclerViewLogements)
        logementAdapter = LogementAdapter(emptyList())

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = logementAdapter

        viewModel.logements.observe(this, Observer { logements ->
            logementAdapter.logements = logements
            logementAdapter.notifyDataSetChanged()
        })

        viewModel.getAllLogements()
    }
}
