package com.example.stustay.Ui.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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


    }
}