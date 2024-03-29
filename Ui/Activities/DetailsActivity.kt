package com.example.stustay.Ui.Activities


import com.example.stustay.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

import com.bumptech.glide.Glide

import com.example.stustay.Ui.Activties.LOGEMENT_CHAMBRES_KEY
import com.example.stustay.Ui.Activties.LOGEMENT_CONTACT_KEY
import com.example.stustay.Ui.Activties.LOGEMENT_DESCRIPTION_KEY
import com.example.stustay.Ui.Activties.LOGEMENT_IMAGES_KEY
import com.example.stustay.Ui.Activties.LOGEMENT_LIEU_KEY
import com.example.stustay.Ui.Activties.LOGEMENT_NOM_KEY
import com.example.stustay.Ui.Activties.LOGEMENT_PRIX_KEY
import com.example.stustay.Ui.Activties.LOGEMENT_SHARED_PREFS
import com.example.stustay.Ui.Activties.LOGEMENT_TITLE_KEY

import com.example.stustay.databinding.ActivityDetailsBinding


class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        val sharedPreferences = getSharedPreferences(LOGEMENT_SHARED_PREFS, MODE_PRIVATE)
        val storedImages = sharedPreferences.getString(LOGEMENT_IMAGES_KEY, null)
        loadImage(storedImages)
        val storedTitle = sharedPreferences.getString(LOGEMENT_TITLE_KEY, null)
        val storedDescription = sharedPreferences.getString(LOGEMENT_DESCRIPTION_KEY, null)
        val storedNom = sharedPreferences.getString(LOGEMENT_NOM_KEY, null)
        val storedChambres = sharedPreferences.getString(LOGEMENT_CHAMBRES_KEY, null)
        val storedPrix = sharedPreferences.getString(LOGEMENT_PRIX_KEY, null)
        val storedContact = sharedPreferences.getString(LOGEMENT_CONTACT_KEY, null)
        val storedLieu = sharedPreferences.getString(LOGEMENT_LIEU_KEY, null)

        binding.tvTitle.text = storedTitle
        binding.tvDescription.text = storedDescription
        binding.tvNom.text = storedNom
        binding.tvChambres.text = storedChambres
        binding.tvPrix.text = storedPrix
        binding.tvContact.text = storedContact
        binding.tvLieu.text = storedLieu

        val btnShare: ImageButton = findViewById(R.id.btnShare)
        btnShare.setOnClickListener {
            shareAnnouncement()
        }

        val btnPayer: Button = findViewById(R.id.buttonP)
        btnPayer.setOnClickListener {
            val intent = Intent(this, PaymentMethodActivity::class.java)
            startActivity(intent)
        }
    }

    private fun shareAnnouncement() {
        val announcementTitle = binding.tvTitle.text.toString()
        val announcementDescription = binding.tvDescription.text.toString()

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, announcementTitle)
        shareIntent.putExtra(Intent.EXTRA_TEXT, announcementDescription)

        startActivity(Intent.createChooser(shareIntent, "Share Announcement"))
    }

    private fun loadImage(imageUrl: String?) {
        if (!imageUrl.isNullOrBlank()) {
            Glide.with(this)
                .load(imageUrl)
                .into(binding.imageDetails)
        } else {
            Glide.with(this)
                .load(R.drawable.home)
                .into(binding.imageDetails)
        }
    }
}
