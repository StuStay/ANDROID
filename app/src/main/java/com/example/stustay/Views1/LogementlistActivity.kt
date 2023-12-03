package com.example.stustay.Views1

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
import com.example.stustay.Model1.Logement
import com.example.stustay.Repository1.LogementRepository
import com.google.gson.JsonObject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tn.esprit.safeguardapplication.Api.LogementApiService


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
        GlobalScope.launch {
            try {
                val logements = withContext(Dispatchers.IO) {
                    LogementRepository.getAllLogements()
                }
                Log.i("Logement:",  "$logements")
                withContext(Dispatchers.Main) {
                    logementAdapter.setData(logements)
                    println("Data set to adapter. Item count: ${logementAdapter.itemCount}")
                }
            } catch (e: Exception) {
                Log.i("error=", e.toString())
                e.printStackTrace()
            }
        }
    }
    private fun createMockService(): LogementApiService {
        return object : LogementApiService {
            override suspend fun createLogement(jsonData: JsonObject) {
            }

            override suspend fun deleteLogement(logementId: String) {
            }

            override suspend fun getAllLogements(): List<Logement> {
                return emptyList()
            }
        }
    }
}
