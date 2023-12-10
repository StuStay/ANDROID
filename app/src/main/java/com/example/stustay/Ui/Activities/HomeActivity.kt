package com.example.stustay.Ui.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.stustay.R
import com.example.stustay.Ui.Activties.PostActivity
import com.example.stustay.ViewModel.getAllLogementsViewModel
import com.example.stustay.databinding.ActivityDetailsBinding
import com.example.stustay.databinding.ActivityHomeBinding
import kotlinx.coroutines.launch

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
    }
    private fun setupSearchBar() {
        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed for this example
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed for this example
            }

            override fun afterTextChanged(s: Editable?) {
                // Filter data based on the search query
                val searchQuery = s.toString().trim()
                filterData(searchQuery)
            }
        })
    }

    private fun filterData(searchQuery: String) {
        // Check the search query and show/hide card views accordingly
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
                // If no match, show all card views
                binding.cardView1.visibility = View.VISIBLE
                binding.cardView2.visibility = View.VISIBLE
                binding.cardView3.visibility = View.VISIBLE
            }
        }
    }
}