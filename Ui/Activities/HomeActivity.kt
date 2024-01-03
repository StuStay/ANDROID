package com.example.stustay.Ui.Activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.stustay.R
import com.example.stustay.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOrderNow1.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnHeart1.setOnClickListener {
            val intent = Intent(this, HeartAnimationActivity::class.java)
            startActivity(intent)
        }

        // Add click listener for the image button to start PayActivity
        binding.imageButton.setOnClickListener {
            val payIntent = Intent(this, HomePayActivity::class.java)
            startActivity(payIntent)
        }
        binding.imageButton2.setOnClickListener {
            val payIntent = Intent(this, MainActivity::class.java)
            startActivity(payIntent)
        }

        setupSearchBar()

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
        // Implement your data filtering logic here if needed
    }
}
