package com.mariazhang.assistoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.mariazhang.assistoapp.database.anuncio_cuidados
import com.mariazhang.assistoapp.databinding.VistaAnuncioCuidadosBinding
import com.mariazhang.assistoapp.interfaces.OnItemClickCuidados


class RecyclerViewAdapterCuidados(

    private val anuncioCuidadosLista: List<anuncio_cuidados>,
    private val listener: OnItemClickCuidados?
    
) : RecyclerView.Adapter<RecyclerViewAdapterCuidados.ViewHolder>() {

    override fun  onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            VistaAnuncioCuidadosBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val anuncioCuidados = anuncioCuidadosLista[position]
        holder.tvEnunciado.text = anuncioCuidados.enunciado
        holder.tvGradoDiscapacidad.text = anuncioCuidados.grado_discapacidad
        holder.tvSalario.text = anuncioCuidados.salario
        holder.tvTipoJornada.text = anuncioCuidados.tipo_jornada
        holder.tvCiudad.text = anuncioCuidados.ciudad

        holder.itemView.tag = anuncioCuidados
        holder.itemView.setOnClickListener(holder)
    }

    
    override fun getItemCount(): Int = anuncioCuidadosLista.size

    inner class ViewHolder(binding: VistaAnuncioCuidadosBinding) : RecyclerView.ViewHolder(binding.root),View.OnClickListener {

        val tvEnunciado: TextView = binding.tvEnunciado
        val tvGradoDiscapacidad: TextView = binding.tvGradodiscapacidad
        val tvSalario: TextView = binding.tvSalario
        val tvTipoJornada: TextView = binding.tvTipoJornada
        val tvCiudad: TextView = binding.tvCiudad

        
        override fun onClick(view: View?) {
            val anuncioCuidados = view?.tag as anuncio_cuidados

            listener?.onItemClick(anuncioCuidados)

        }
    }



}
