package com.example.stustay

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class profile : AppCompatActivity() {
    private lateinit var avatarImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var rolesTextView: TextView
    private lateinit var modifierButton : Button
    private lateinit var deconnecterButton: Button
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Retrieve user information from SharedPreferences
        val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "DefaultName")
        val lastName = sharedPreferences.getString("last_name", "DefaultLastName")

        val avatarUrl = sharedPreferences.getString("avatar", "")
        val role = sharedPreferences.getString("role", "")
        // Log in saveUserInSharedPreferences

// Log in onCreate of profile activity
        Log.d("SharedPreferences", "Retrieving user: $name $lastName $avatarUrl $role")

        // Initialize views
        avatarImageView = findViewById(R.id.avatarImageView)
        nameTextView = findViewById(R.id.nameTextView)
        rolesTextView = findViewById(R.id.rolesTextView)

        modifierButton = findViewById(R.id.modifierProfilButton)
        deconnecterButton = findViewById(R.id.deconnecterButton)

        modifierButton.setOnClickListener {
            Log.d("aaaaaaaaaaa","hhh")
            val intent = Intent(this@profile, updateProfil::class.java)
            this.startActivity(intent)
        }
        deconnecterButton.setOnClickListener {
            // Access the editor to modify SharedPreferences
            val editor = sharedPreferences.edit()

            // Clear all data in SharedPreferences
            editor.clear()

            // Apply changes
            editor.apply()

            val intent = Intent(this@profile, login::class.java)
            this.startActivity(intent)
        }

        // Load user's avatar using Glide
        Glide.with(this)
            .load(avatarUrl)
            .into(avatarImageView)

        // Set the text of nameTextView to be the combination of name and last name
        nameTextView.text = "$name $lastName"

        // Set the text of rolesTextView
        rolesTextView.text = role
    }
}
