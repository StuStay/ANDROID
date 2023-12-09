package com.example.stustay.Ui.Activties


import LogementRepositoryImpl
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.stustay.R
import com.example.stustay.Repository.LogementRepository
import com.example.stustay.Repository.RetrofitInstance
import com.example.stustay.Ui.Activities.DetailsActivity
import com.example.stustay.Ui.Activities.HomeActivity
import com.example.stustay.ViewModel.PostActivityViewModel
import com.example.stustay.databinding.ActivityPostBinding

const val POST_TAG = "Post Activity"
const val LOGEMENT_SHARED_PREFS = "logement_shared_prefs"
const val LOGEMENT_TITLE_KEY = "logement_title"
const val LOGEMENT_DESCRIPTION_KEY = "logement_description"
const val LOGEMENT_NOM_KEY ="nom_chambres"

// Add more keys for other logement properties here
const val LOGEMENT_CHAMBRES_KEY ="Logement_chambres"
const val LOGEMENT_PRIX_KEY ="prix_chambres"
const val LOGEMENT_CONTACT_KEY ="contact_chambres"
const val LOGEMENT_LIEU_KEY ="lieu_chambres"




class PostActivity : ComponentActivity() {

    private lateinit var binding: ActivityPostBinding
    private lateinit var viewModel: PostActivityViewModel
    private lateinit var sharedPreferences: SharedPreferences
    companion object {
        val IMAGE_REQUEST_CODE = 1_000;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(POST_TAG, "onCreate: PostActivity created")
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(LOGEMENT_SHARED_PREFS, MODE_PRIVATE)

        val logementRepository: LogementRepository = LogementRepositoryImpl(RetrofitInstance.api)

        val LogementViewModelFactory = PostActivityViewModel.Factory(logementRepository)

        viewModel = ViewModelProvider(this, LogementViewModelFactory).get(PostActivityViewModel::class.java)
        observePostLogement()

        binding.btnPickImg.setOnClickListener {
            pickImageFromGallery()
        }
    }
    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            binding.imgSave.setImageURI(data?.data)
        }
    }
    private fun observePostLogement() {
        Log.d(POST_TAG, "observeLogement: Button Clicked!")

        findViewById<Button>(R.id.btnPostLogement).setOnClickListener {
            Log.d(POST_TAG,"hello")
            val titre = binding.etTitre.text.toString()
            val description = binding.etDescription.text.toString()
            val nom = binding.etNom.text.toString()
            val nombreChambre = binding.etNombreChambre.text.toString()
            val prix = binding.etPrix.text.toString()

            val contact = binding.etContact.text.toString()

            val lieu = binding.etLieu.text.toString()


            Toast.makeText(this@PostActivity, "Button Clicked!", Toast.LENGTH_SHORT).show()

            viewModel.createLogement("", titre, description, nom, nombreChambre.toInt(), prix.toDouble(), contact, lieu).observe(this@PostActivity) { response ->
                if (response != null) {
                    // Save logement information to SharedPreferences
                    saveLogementInformation(titre, description,nom,nombreChambre.toInt(),prix.toDouble(),contact,lieu)
                    // Start DetailsViewActivity
                    val intent = Intent(this@PostActivity, DetailsActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Log.d("Post Activity", "error")
                }
            }
        }
    }

    private fun saveLogementInformation(title: String, description: String,nom:String,nombreChambre:Int,prix:Double,contact:String,lieu:String) {
        val editor = sharedPreferences.edit()
        editor.putString(LOGEMENT_TITLE_KEY, title)
        editor.putString(LOGEMENT_DESCRIPTION_KEY, description)
        editor.putString(LOGEMENT_NOM_KEY, nom)
        editor.putString(LOGEMENT_CHAMBRES_KEY, nombreChambre.toString())
        editor.putString(LOGEMENT_PRIX_KEY, prix.toString())
        editor.putString(LOGEMENT_CONTACT_KEY, contact)
        editor.putString(LOGEMENT_LIEU_KEY, lieu)







        editor.apply()
    }
}