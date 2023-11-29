import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.stustay.Model.Logement
import com.example.stustay.R


// LogementAdapter.kt
class LogementAdapter(var logements: List<Logement>) :
    RecyclerView.Adapter<LogementAdapter.LogementViewHolder>() {

    class LogementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewLogement: ImageView = itemView.findViewById(R.id.imageViewLogement)
        val textViewTitre: TextView = itemView.findViewById(R.id.textViewTitre)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogementViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_logement, parent, false)
        return LogementViewHolder(view)
    }

    override fun onBindViewHolder(holder: LogementViewHolder, position: Int) {
        val logement = logements[position]



        holder.textViewTitre.text = logement.titre

    }

    override fun getItemCount(): Int {
        return logements.size
    }
}

