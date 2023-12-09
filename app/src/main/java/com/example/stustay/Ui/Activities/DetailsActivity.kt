package com.example.stustay.Ui.Activities

import com.example.stustay.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.stustay.Ui.Activties.LOGEMENT_CHAMBRES_KEY
import com.example.stustay.Ui.Activties.LOGEMENT_CONTACT_KEY
import com.example.stustay.Ui.Activties.LOGEMENT_DESCRIPTION_KEY
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


        // Retrieve the stored logement information from SharedPreferences
        val sharedPreferences = getSharedPreferences(LOGEMENT_SHARED_PREFS, MODE_PRIVATE)
        val storedTitle = sharedPreferences.getString(LOGEMENT_TITLE_KEY, null)
        val storedDescription = sharedPreferences.getString(LOGEMENT_DESCRIPTION_KEY, null)
        val storedNom = sharedPreferences.getString(LOGEMENT_NOM_KEY, null)
        val storedChambres = sharedPreferences.getString(LOGEMENT_CHAMBRES_KEY,null)
        val storedPrix = sharedPreferences.getString(LOGEMENT_PRIX_KEY, null)

        val storedContact = sharedPreferences.getString(LOGEMENT_CONTACT_KEY, null)
        val storedLieu = sharedPreferences.getString(LOGEMENT_LIEU_KEY, null)



        val stored = sharedPreferences.getString(LOGEMENT_CHAMBRES_KEY,null)

        // Set the retrieved logement information to the TextViews
        binding.tvTitle.text = storedTitle
        binding.tvDescription.text = storedDescription
        binding.tvNom.text = storedNom
        binding.tvChambres.text=storedChambres
        binding.tvPrix.text=storedPrix
        binding.tvContact.text=storedContact
        binding.tvLieu.text=storedLieu

        // ...

        // Call the getAllLogements function and observe the LiveData


        val btnShare: ImageButton = findViewById(R.id.btnShare)
        btnShare.setOnClickListener {
            shareAnnouncement()
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
}