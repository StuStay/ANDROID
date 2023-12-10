package com.example.stustay.Ui.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View

import com.bumptech.glide.Glide

import com.example.stustay.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnOrderNow1.setOnClickListener {
            val   intent = Intent(this , DetailsActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnHeart1.setOnClickListener {
            val intent = Intent(this, HeartAnimationActivity::class.java)
            startActivity(intent)
        }

        setupSearchBar()
        binding.btnOrderNow1.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
            finish()
        }
        val imageFromDetails = intent.getStringExtra("LOGEMENT_IMAGES_KEY")
        if (!imageFromDetails.isNullOrBlank()) {

            Glide.with(this)
                .load(imageFromDetails)
                .into(binding.image1)
        }
    }
    private fun setupSearchBar() {
        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val searchQuery = s.toString().trim()
                filterData(searchQuery)
            }
        })
    }

    private fun filterData(searchQuery: String) {
        when {
            searchQuery.equals("Ariana", ignoreCase = true) -> {
                binding.cardView1.visibility = View.VISIBLE
                binding.cardView2.visibility = View.GONE
                binding.cardView3.visibility = View.GONE
            }
            searchQuery.equals("Studio", ignoreCase = true) -> {
                binding.cardView1.visibility = View.GONE
                binding.cardView2.visibility = View.VISIBLE
                binding.cardView3.visibility = View.GONE
            }
            searchQuery.equals("Mourouj", ignoreCase = true) -> {
                binding.cardView1.visibility = View.GONE
                binding.cardView2.visibility = View.GONE
                binding.cardView3.visibility = View.VISIBLE
            }
            else -> {
                binding.cardView1.visibility = View.VISIBLE
                binding.cardView2.visibility = View.VISIBLE
                binding.cardView3.visibility = View.VISIBLE
            }
        }
    }
}