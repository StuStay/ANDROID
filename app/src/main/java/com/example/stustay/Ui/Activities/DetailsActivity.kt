package com.example.stustay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val btnShare: ImageButton = findViewById(R.id.btnShare)


        btnShare.setOnClickListener {
            shareAnnouncement()
        }


    }

    private fun shareAnnouncement() {

        val announcementTitle = "Your Announcement Title"
        val announcementDescription = "Your Announcement Description"


        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, announcementTitle)
        shareIntent.putExtra(Intent.EXTRA_TEXT, announcementDescription)


        startActivity(Intent.createChooser(shareIntent, "Share Announcement"))
    }
}