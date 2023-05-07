package com.mariazhang.assistoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.mariazhang.assistoapp.Drawer4Fragment
import com.mariazhang.assistoapp.database.anuncio_asistentes
import com.mariazhang.assistoapp.databinding.FragmentDrawer4Binding
import com.mariazhang.assistoapp.databinding.VistaAnuncioAsisBinding
import com.mariazhang.assistoapp.interfaces.OnItemClickAsistentes


class RecyclerViewAdapter(

    private val anuncioAsistenteLista: List<anuncio_asistentes>,
    private val listener: OnItemClickAsistentes?
    
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun  onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            VistaAnuncioAsisBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val anuncioAsistente = anuncioAsistenteLista[position]
        holder.tvEnunciado.text = anuncioAsistente.enunciado
        holder.tvDisponibilidad.text = anuncioAsistente.disponibilidad
        holder.tvSalario.text = anuncioAsistente.salario
        holder.tvExperiencia.text = anuncioAsistente.experiencia
        holder.tvCiudad.text = anuncioAsistente.ciudad

        holder.itemView.tag = anuncioAsistente
        holder.itemView.setOnClickListener(holder)
    }

    
    override fun getItemCount(): Int = anuncioAsistenteLista.size

    inner class ViewHolder(binding: VistaAnuncioAsisBinding) : RecyclerView.ViewHolder(binding.root),View.OnClickListener {

        val tvEnunciado: TextView = binding.tvEnunciado
        val tvDisponibilidad: TextView = binding.tvDisponibilidad
        val tvSalario: TextView = binding.tvSalario
        val tvExperiencia: TextView = binding.tvExperiencia
        val tvCiudad: TextView = binding.tvCiudad

        
        override fun onClick(view: View?) {
            val anuncio_asistentes = view?.tag as anuncio_asistentes

            listener?.onItemClick(anuncio_asistentes)

        }
    }



}
