package com.example.stustay.Views

import LogementAdapter
import com.example.stustay.R
import tn.esprit.logement.viewmodels.LogementViewModel


import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.DelicateCoroutinesApi


class LogementListActivity : AppCompatActivity() {

    private lateinit var logementAdapter: LogementAdapter
    private lateinit var logementViewModel: LogementViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logementlist)

        val recyclerViewLogements = findViewById<RecyclerView>(R.id.recyclerView)
        logementAdapter = LogementAdapter(this@LogementListActivity, emptyList())
        recyclerViewLogements.adapter = logementAdapter
        recyclerViewLogements.layoutManager = LinearLayoutManager(this)

        logementViewModel = ViewModelProvider(this).get(LogementViewModel::class.java)

        loadLogements()

        val closeBtn = findViewById<Button>(R.id.closebtn2)
        closeBtn.setOnClickListener {
            finish()
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
    private fun loadLogements() {

        logementViewModel.getAllLogements().observe(this, { logements ->
            logementAdapter.setLogements(logements)
            Log.i("Received logements:",  "$logements")
        })
    }
}
