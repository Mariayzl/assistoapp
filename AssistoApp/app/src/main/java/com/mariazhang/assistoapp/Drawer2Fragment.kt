package com.mariazhang.assistoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.mariazhang.assistoapp.adapters.RecyclerViewAdapterAsistente
import com.mariazhang.assistoapp.database.anuncio_asistentes
import com.mariazhang.assistoapp.databinding.ActivityIniciarcuentaBinding
import com.mariazhang.assistoapp.databinding.FragmentDrawer2Binding
import com.mariazhang.assistoapp.interfaces.OnItemClickAsistentes

class Drawer2Fragment : Fragment() {
    var listener: OnItemClickAsistentes? = null

    private lateinit var binding: FragmentDrawer2Binding

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnItemClickAsistentes) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()

        listener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        var usuarioPreferencia =
            requireContext().getSharedPreferences(
                "vistaSuperficial",
                AppCompatActivity.MODE_PRIVATE
            )
        usuarioPreferencia?.edit()
            ?.putBoolean("check", true)
            ?.apply()

        val view = inflater.inflate(R.layout.fragment_drawer2, container, false)
        val firestore = FirebaseFirestore.getInstance()
        var listaAnuncios: MutableList<anuncio_asistentes> = mutableListOf()

        firestore.collection("anuncio_asistente").get()
            .addOnSuccessListener { documents ->

                for (document in documents) {
                    listaAnuncios.add(anuncio_asistentes.fromJson(document.data))

                }

                val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerviewAsistente)

                recyclerView.layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                recyclerView.adapter = RecyclerViewAdapterAsistente(listaAnuncios, listener)

                println(listaAnuncios.size.toString() + "Proceso PRUEBAS PRUEBAS PRINT-anuncio_asistente--")

            }

            .addOnFailureListener { exception ->
                Log.i(
                    "Proceso PRUEBAS PRUEBAS LOG.I LOG.I-anuncio_asistente",
                    "Error getting documents: ",
                    exception
                )
            }

        return FragmentDrawer2Binding.inflate(inflater, container, false).also { binding = it }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val buttonCrearAsistente = view.findViewById<Button>(R.id.buttonCrear1)

        buttonCrearAsistente.setOnClickListener() {
            val intent = Intent(view.context, CrearAnuncioAsistenteActivity::class.java)
            startActivity(intent)
        }

        val spinner = view.findViewById<Spinner>(R.id.spinnerAsistente)
        val opciones = listOf("Todos", "Ciudad", "Disponibilidad", "Experiencia")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val firestore = FirebaseFirestore.getInstance()

        val buttonFiltroAsistente = view.findViewById<Button>(R.id.buttonFiltroAsistente)

        buttonFiltroAsistente.setOnClickListener() {

            print(spinner.selectedItem.toString())
            Log.i(
                "PRUEBAS LOG.I - PRUEBAS LOG.I - FILTRO ASISTENTE",
                spinner.selectedItem.toString()
            )

            when (spinner.selectedItem.toString()) {

                "Ciudad" -> {

                    var listaFiltroAnuncio = mutableListOf<anuncio_asistentes>()

                    firestore.collection("anuncio_asistente")
                        .whereEqualTo("ciudad", binding.editTextTextPersonName.text.toString())
                        .get()

                        .addOnSuccessListener { documents ->

                            for (document in documents) {
                                listaFiltroAnuncio.add(anuncio_asistentes.fromJson(document.data))

                            }

                            val recyclerView =
                                view.findViewById<RecyclerView>(R.id.recyclerviewAsistente)

                            recyclerView.layoutManager = when {
                                columnCount <= 1 -> LinearLayoutManager(context)
                                else -> GridLayoutManager(context, columnCount)
                            }

                            recyclerView.adapter =
                                RecyclerViewAdapterAsistente(listaFiltroAnuncio, listener)

                            println(listaFiltroAnuncio.size.toString() + "PRUEBAS PRINT PRINT - FILTRO ASISTENTE-Ciudad--")

                        }

                        .addOnFailureListener { exception ->
                            Log.i(
                                "PRUEBAS LOG.I - EXCEPTION - FILTRO ASISTENTE-Ciudad",
                                "Error getting documents: ",
                                exception
                            )
                        }
                }

                "Disponibilidad" -> {

                    var listaFiltroAnuncio = mutableListOf<anuncio_asistentes>()

                    firestore.collection("anuncio_asistente").whereEqualTo(
                        "disponibilidad",
                        binding.editTextTextPersonName.text.toString()
                    ).get()

                        .addOnSuccessListener { documents ->

                            for (document in documents) {
                                listaFiltroAnuncio.add(anuncio_asistentes.fromJson(document.data))

                            }

                            val recyclerView =
                                view.findViewById<RecyclerView>(R.id.recyclerviewAsistente)

                            recyclerView.layoutManager = when {
                                columnCount <= 1 -> LinearLayoutManager(context)
                                else -> GridLayoutManager(context, columnCount)
                            }

                            recyclerView.adapter =
                                RecyclerViewAdapterAsistente(listaFiltroAnuncio, listener)

                            println(listaFiltroAnuncio.size.toString() + "PRUEBAS PRINT PRINT - FILTRO ASISTENTE-Disponibilidad--")

                        }

                        .addOnFailureListener { exception ->
                            Log.i(
                                "PRUEBAS LOG.I - EXCEPTION - FILTRO ASISTENTE-Disponibilidad",
                                "Error getting documents: ",
                                exception
                            )
                        }
                }

                "Experiencia" -> {

                    var listaFiltroAnuncio = mutableListOf<anuncio_asistentes>()

                    firestore.collection("anuncio_asistente")
                        .whereEqualTo("experiencia", binding.editTextTextPersonName.text.toString())
                        .get()

                        .addOnSuccessListener { documents ->

                            for (document in documents) {
                                listaFiltroAnuncio.add(anuncio_asistentes.fromJson(document.data))

                            }

                            val recyclerView =
                                view.findViewById<RecyclerView>(R.id.recyclerviewAsistente)

                            recyclerView.layoutManager = when {
                                columnCount <= 1 -> LinearLayoutManager(context)
                                else -> GridLayoutManager(context, columnCount)
                            }

                            recyclerView.adapter =
                                RecyclerViewAdapterAsistente(listaFiltroAnuncio, listener)

                            println(listaFiltroAnuncio.size.toString() + "PRUEBAS PRINT PRINT - FILTRO ASISTENTE-Experiencia---")

                        }

                        .addOnFailureListener { exception ->
                            Log.i(
                                "PRUEBAS LOG.I - EXCEPTION - FILTRO ASISTENTE-Experiencia",
                                "Error getting documents: ",
                                exception
                            )
                        }
                }

                "Todos" -> {

                    var listaFiltroAnuncio = mutableListOf<anuncio_asistentes>()

                    firestore.collection("anuncio_asistente").get()

                        .addOnSuccessListener { documents ->

                            for (document in documents) {
                                listaFiltroAnuncio.add(anuncio_asistentes.fromJson(document.data))

                            }

                            val recyclerView =
                                view.findViewById<RecyclerView>(R.id.recyclerviewAsistente)

                            recyclerView.layoutManager = when {
                                columnCount <= 1 -> LinearLayoutManager(context)
                                else -> GridLayoutManager(context, columnCount)
                            }

                            recyclerView.adapter =
                                RecyclerViewAdapterAsistente(listaFiltroAnuncio, listener)

                            println(listaFiltroAnuncio.size.toString() + "PRUEBAS PRINT PRINT - FILTRO ASISTENTE-Todos---")

                        }

                        .addOnFailureListener { exception ->
                            Log.i(
                                "PRUEBAS LOG.I - EXCEPTION - FILTRO ASISTENTE-Todos",
                                "Error getting documents: ",
                                exception
                            )
                        }

                }

            }
        }

    }


    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            Drawer2Fragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}