package com.example.stustay.Ui.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.stustay.R

class LaunchScreenActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_screen)

        // Utilisez un Handler pour retarder le lancement de l'activité principale
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Ferme l'activité actuelle pour empêcher le retour à l'écran de lancement
        }, SPLASH_TIME_OUT)
    }
}
