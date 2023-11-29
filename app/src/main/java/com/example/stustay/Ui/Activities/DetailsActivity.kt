package com.example.stustay.Ui.Activities

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.stustay.R

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details)

        // Récupérer les données de l'intent
        val intent = intent
        val images = intent.getStringExtra("images") ?: ""
        val titre = intent.getStringExtra("titre") ?: ""
        val description = intent.getStringExtra("description") ?: ""
        val nom = intent.getStringExtra("nom") ?: ""
        val nombreChambre = intent.getIntExtra("nombreChambre", 0)
        val prix = intent.getDoubleExtra("prix", 0.0)
        val contact = intent.getStringExtra("contact") ?: ""
        val lieu = intent.getStringExtra("lieu") ?: ""

        // Remplir les éléments de l'interface utilisateur avec les données
        val imageDetails: ImageView = findViewById(R.id.imageDetails)
        // Utilisez une bibliothèque comme Picasso ou Glide pour charger l'image à partir de l'URL
        // Picasso.get().load(images).into(imageDetails)

        val tvTitle: TextView = findViewById(R.id.tvTitle)
        val tvDescription: TextView = findViewById(R.id.tvDescription)
        val tvNom: TextView = findViewById(R.id.tvNom)
        val tvChambres: TextView = findViewById(R.id.tvChambres)
        val tvPrix: TextView = findViewById(R.id.tvPrix)
        val tvContact: TextView = findViewById(R.id.tvContact)
        val tvLieu: TextView = findViewById(R.id.tvLieu)
        val btnPayer: Button = findViewById(R.id.btnPayer)
        val btnShare: ImageButton = findViewById(R.id.btnShare)

        tvTitle.text = titre
        tvDescription.text = description
        tvNom.text = nom
        tvChambres.text = "Chambres: $nombreChambre"
        tvPrix.text = "Prix: $prix"
        tvContact.text = contact
        tvLieu.text = lieu

        // Ajouter le code pour le bouton "Payer" ici
        btnPayer.setOnClickListener {
            // Ajoutez ici le code pour gérer le clic sur le bouton "Payer"
        }

        // Ajouter le code pour le bouton "Share" ici
        btnShare.setOnClickListener {
            // Ajoutez ici le code pour gérer le clic sur le bouton "Share"
        }
    }
}
