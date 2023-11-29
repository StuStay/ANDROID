import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.stustay.R
import kotlinx.android.synthetic.main.activity_postulation.*
import android.content.res.Resources
import android.widget.LinearLayout

import androidx.core.view.marginTop

class PostulationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post)


        btnSubmit.setOnClickListener {

            val images = etImages.text.toString()
            val titre = etTitre.text.toString()
            val description = etDescription.text.toString()
            val nom = etNom.text.toString()
            val nombreChambre = etNombreChambre.text.toString().toIntOrNull() ?: 0
            val prix = etPrix.text.toString().toDoubleOrNull() ?: 0.0
            val contact = etContact.text.toString()
            val lieu = etLieu.text.toString()



            addNewLogement(images, titre, description, nom, nombreChambre, prix, contact, lieu)
        }


        btnChooseLocation.setOnClickListener {

        }
    }


    private fun addNewLogement(
        images: String,
        titre: String,
        description: String,
        nom: String,
        nombreChambre: Int,
        prix: Double,
        contact: String,
        lieu: String
    ) {
        val newCardView = CardView(this)
        newCardView.layoutParams = CardView.LayoutParams(
            CardView.LayoutParams.MATCH_PARENT,
            CardView.LayoutParams.WRAP_CONTENT
        )
        newCardView.cardElevation = 8f
        newCardView.radius = 16f
        newCardView.setContentPadding(16, 16, 16, 16)

        val newRelativeLayout = RelativeLayout(this)
        newRelativeLayout.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )




        val newImageView = ImageView(this)
        newImageView.layoutParams = RelativeLayout.LayoutParams(
            287.dpToPx(), 197.dpToPx()
        )
        newImageView.id = View.generateViewId()
        newImageView.scaleType = ImageView.ScaleType.CENTER_CROP
        newImageView.setImageResource(R.drawable.logo)



        newRelativeLayout.addView(newImageView)


        newCardView.addView(newRelativeLayout)


        linearContainer.addView(newCardView)


        resetForm()
    }


    private fun resetForm() {
        etImages.text.clear()
        etTitre.text.clear()
        etDescription.text.clear()
        etNom.text.clear()
        etNombreChambre.text.clear()
        etPrix.text.clear()
        etContact.text.clear()
        etLieu.text.clear()
    }


    fun Int.dpToPx(): Int {
        val density = resources.displayMetrics.density
        return (this * density).toInt()
    }
}
