/*package com.example.stustay.Views

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stustay.Api.LogementApiService
import com.example.stustay.Model.Logement
import com.example.stustay.R
import com.example.stustay.Repository.LogementRepository
import com.example.stustay.Ui.Adapters.LogementAdapter

import com.google.gson.JsonObject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LogementListActivity : AppCompatActivity() {

    private lateinit var logementAdapter: LogementAdapter
    private lateinit var logementRepository: LogementRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logementlist)

        Log.i("logement page", "this is the logement page")
        val recyclerViewLogements = findViewById<RecyclerView>(R.id.recyclerView)
        logementAdapter = LogementAdapter(this@LogementListActivity, emptyList())
        recyclerViewLogements.adapter = logementAdapter
        recyclerViewLogements.layoutManager = LinearLayoutManager(this)

        logementRepository = LogementRepository(LogementRepository.buildService())

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
}*/