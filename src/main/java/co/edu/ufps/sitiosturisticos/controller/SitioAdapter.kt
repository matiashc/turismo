package co.edu.ufps.sitiosturisticos.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.edu.ufps.sitiosturisticos.R
import co.edu.ufps.sitiosturisticos.modelo.Sitio
import com.squareup.picasso.Picasso

class SitioAdapter(var context: Context?, val dataSet: ArrayList<Sitio>, recurso: Int):RecyclerView.Adapter<SitioAdapter.SitioViewHolder>() {
    class SitioViewHolder(val view: View): RecyclerView.ViewHolder(view){
        var imagen: ImageView
        var nombre: TextView
        var descripcion: TextView
        init {
            imagen = view.findViewById(R.id.imagen)
            nombre = view.findViewById(R.id.nombre)
            descripcion = view.findViewById(R.id.descripcion)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SitioViewHolder {
        val layoutinflater= LayoutInflater.from(parent.context)
        val view: View = layoutinflater.inflate(R.layout.card,parent,false)
        return SitioViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: SitioViewHolder, position: Int) {
        holder.nombre.setText(dataSet.get(position).nombre)
        holder.descripcion.setText(dataSet.get(position).descripcion)
        Picasso.get().load(dataSet.get(position).imagen).into(holder.imagen);
    }
}