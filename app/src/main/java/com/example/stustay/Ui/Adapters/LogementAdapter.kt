/*package com.example.stustay.Ui.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stustay.Model.Logement
import com.example.stustay.databinding.ActivityPostBinding
import com.example.stustay.databinding.ItemLogementBinding

class LogementAdapter(
    private var context: Context,
    private var logements: List<Logement>
    private  var  binding: ActivityPostBinding
) : RecyclerView.Adapter<LogementAdapter.LogementViewHolder>() {
    inner class LogementViewHolder(binding: ItemLogementBinding) : RecyclerView.ViewHolder(binding.root) {
        val etTitre: TextView = binding.etTitre
        val etDescription: TextView = binding.etDescription
        val etNom: TextView = binding.etNom
        val etNombreChambre: TextView = binding.etNombreChambre
        val etPrix: TextView = binding.etPrix
        val etContact: TextView = binding.etContact
        val etLieu: TextView = binding.etLieu

        /*fun bind(logement: Logement) {
            etTitre.text = logement.titre
            etDescription.text = logement.description
            etNom.text = logement.nom
            etNombreChambre.text = logement.nombreChambre.toString()
            etPrix.text = logement.prix.toString()
            etContact.text = logement.contact
            etLieu.text = logement.lieu
        }*/
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogementViewHolder {
        val binding = ItemLogementBinding.inflate(LayoutInflater.from(context), parent, false)
        return LogementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LogementViewHolder, position: Int) {
        val currentLogement: Logement = logements[position]

        holder.etTitre.text = currentLogement.titre
        holder.etDescription.text = currentLogement.description
        holder.etNom.text = currentLogement.nom
        holder.etNombreChambre.text = currentLogement.nombreChambre.toString()
        holder.etPrix.text = currentLogement.prix.toString()
        holder.etContact.text = currentLogement.contact
        holder.etLieu.text = currentLogement.lieu


    }

    override fun getItemCount(): Int {
        return logements.size
    }

    fun setLogements(logements: List<Logement>) {
        this.logements = logements
        notifyDataSetChanged()
    }
}*/