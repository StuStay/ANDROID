package com.example.stustay

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class PostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
    }


    fun onSubmitClick(view: View) {

        val titre = findViewById<TextInputEditText>(R.id.etTitre).text.toString()
        val description = findViewById<TextInputEditText>(R.id.etDescription).text.toString()
        val nom = findViewById<TextInputEditText>(R.id.etNom).text.toString()
        val nombreChambre = findViewById<TextInputEditText>(R.id.etNombreChambre).text.toString()
        val prix = findViewById<TextInputEditText>(R.id.etPrix).text.toString()
        val contact = findViewById<TextInputEditText>(R.id.etContact).text.toString()
        val lieu = findViewById<TextInputEditText>(R.id.etLieu).text.toString()

        if (titre.isEmpty() || description.isEmpty() || nom.isEmpty() || nombreChambre.isEmpty() ||
            prix.isEmpty() || contact.isEmpty() || lieu.isEmpty()) {

            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("TITRE", titre)
        intent.putExtra("DESCRIPTION", description)
        intent.putExtra("NOM", nom)
        intent.putExtra("NOMBRE_CHAMBRE", nombreChambre)
        intent.putExtra("PRIX", prix)
        intent.putExtra("CONTACT", contact)
        intent.putExtra("LIEU", lieu)

        startActivity(intent)
    }
}
