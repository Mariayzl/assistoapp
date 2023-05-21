package com.mariazhang.assistoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.mariazhang.assistoapp.adapters.RecyclerViewAdapterCuidados
import com.mariazhang.assistoapp.database.anuncio_cuidados
import com.mariazhang.assistoapp.databinding.FragmentDrawer3Binding
import com.mariazhang.assistoapp.interfaces.OnItemClickCuidados


class Drawer3Fragment : Fragment() {
    var listener: OnItemClickCuidados? = null

    private lateinit var binding: FragmentDrawer3Binding

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnItemClickCuidados) {
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

        val view = inflater.inflate(R.layout.fragment_drawer3, container, false)

        val firestore = FirebaseFirestore.getInstance()

        val listaAnuncios: MutableList<anuncio_cuidados> = mutableListOf()

        firestore.collection("anuncio_cuidados").get()
            .addOnSuccessListener { documents ->

                for (document in documents) {

                    listaAnuncios.add(anuncio_cuidados.fromJson(document.data))

                }

                val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerviewCuidados)

                recyclerView.layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                recyclerView.adapter = RecyclerViewAdapterCuidados(listaAnuncios, listener)
                println(listaAnuncios.size.toString() + "Proceso PRUEBAS PRUEBAS PRINT-anuncio_cuidados--")

            }

            .addOnFailureListener { exception ->
                Log.i("Proceso PRUEBAS PRUEBAS LOG.I LOG.I - anuncio_cuidados", "Error getting documents: ", exception)
            }

        return FragmentDrawer3Binding.inflate(inflater,container,false).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val buttonCrearCuidados = view.findViewById<Button>(R.id.buttonCrear2)

        buttonCrearCuidados.setOnClickListener() {
            val intent = Intent(view.context, CrearAnuncioCuidadosActivity::class.java)
            startActivity(intent)
        }


        val spinner = view.findViewById<Spinner>(R.id.spinnerCuidados)
        val opciones = listOf("Todos", "Ciudad", "Grado_Discapacidad", "Tipo_Jornada")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item , opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val firestore = FirebaseFirestore.getInstance()

        val buttonFiltroCuidados = view.findViewById<Button>(R.id.buttonFiltroCuidados)

        buttonFiltroCuidados.setOnClickListener() {

            print(spinner.selectedItem.toString())
            Log.i("PRUEBAS LOG.I - PRUEBAS LOG.I - FILTRO CUIDADOS", spinner.selectedItem.toString())

            when (spinner.selectedItem.toString()) {

                "Ciudad" -> {

                    var listaFiltroAnuncio = mutableListOf<anuncio_cuidados>()

                    firestore.collection("anuncio_cuidados").whereEqualTo("ciudad", binding.etbusqueda2.text.toString()).get()

                        .addOnSuccessListener { documents ->

                            for (document in documents) {
                                listaFiltroAnuncio.add(anuncio_cuidados.fromJson(document.data))

                            }

                            val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerviewCuidados)

                            recyclerView.layoutManager = when {
                                columnCount <= 1 -> LinearLayoutManager(context)
                                else -> GridLayoutManager(context, columnCount)
                            }

                            recyclerView.adapter = RecyclerViewAdapterCuidados(listaFiltroAnuncio, listener)

                            println(listaFiltroAnuncio.size.toString() + "PRUEBAS PRINT PRINT - FILTRO CUIDADOS-Ciudad--")

                        }

                        .addOnFailureListener { exception ->
                            Log.i("PRUEBAS LOG.I - EXCEPTION - FILTRO CUIDADOS-Ciudad", "Error getting documents: ", exception)
                        }

                }

                "Grado_Discapacidad" -> {

                    var listaFiltroAnuncio = mutableListOf<anuncio_cuidados>()

                    firestore.collection("anuncio_cuidados")
                        .whereEqualTo("grado_discapacidad", binding.etbusqueda2.text.toString())
                        .get()

                        .addOnSuccessListener { documents ->

                            for (document in documents) {
                                listaFiltroAnuncio.add(anuncio_cuidados.fromJson(document.data))

                            }

                            val recyclerView =
                                view.findViewById<RecyclerView>(R.id.recyclerviewCuidados)

                            recyclerView.layoutManager = when {
                                columnCount <= 1 -> LinearLayoutManager(context)
                                else -> GridLayoutManager(context, columnCount)
                            }

                            recyclerView.adapter =
                                RecyclerViewAdapterCuidados(listaFiltroAnuncio, listener)

                            println(listaFiltroAnuncio.size.toString() + "PRUEBAS PRINT PRINT - FILTRO CUIDADOS-Grado_Discapacidad--")

                        }

                        .addOnFailureListener { exception ->
                            Log.i("PRUEBAS LOG.I - EXCEPTION - FILTRO CUIDADOS-Grado_Discapacidad", "Error getting documents: ", exception)
                        }


                }

                "Tipo_Jornada" -> {

                    var listaFiltroAnuncio = mutableListOf<anuncio_cuidados>()

                    firestore.collection("anuncio_cuidados").whereEqualTo(
                        "tipo_jornada",
                        binding.etbusqueda2.text.toString()
                    ).get()

                        .addOnSuccessListener { documents ->

                            for (document in documents) {
                                listaFiltroAnuncio.add(anuncio_cuidados.fromJson(document.data))

                            }

                            val recyclerView =
                                view.findViewById<RecyclerView>(R.id.recyclerviewCuidados)

                            recyclerView.layoutManager = when {
                                columnCount <= 1 -> LinearLayoutManager(context)
                                else -> GridLayoutManager(context, columnCount)
                            }

                            recyclerView.adapter =
                                RecyclerViewAdapterCuidados(listaFiltroAnuncio, listener)

                            println(listaFiltroAnuncio.size.toString() + "PRUEBAS PRINT PRINT - FILTRO CUIDADOS-Tipo_Jornada--")

                        }

                        .addOnFailureListener { exception ->
                            Log.i("PRUEBAS LOG.I - EXCEPTION - FILTRO CUIDADOS-Tipo_Jornada", "Error getting documents: ", exception)
                        }

                }

                "Todos" -> {

                    var listaFiltroAnuncio = mutableListOf<anuncio_cuidados>()

                    firestore.collection("anuncio_cuidados").get()

                        .addOnSuccessListener { documents ->

                            for (document in documents) {
                                listaFiltroAnuncio.add(anuncio_cuidados.fromJson(document.data))

                            }

                            val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerviewCuidados)

                            recyclerView.layoutManager = when {
                                columnCount <= 1 -> LinearLayoutManager(context)
                                else -> GridLayoutManager(context, columnCount)
                            }

                            recyclerView.adapter = RecyclerViewAdapterCuidados(listaFiltroAnuncio, listener)

                            println(listaFiltroAnuncio.size.toString() + "PRUEBAS PRINT PRINT - FILTRO CUIDADOS-Todos--")

                        }

                        .addOnFailureListener { exception ->
                            Log.i("PRUEBAS LOG.I - EXCEPTION - FILTRO CUIDADOS-Todos", "Error getting documents: ", exception)
                        }
                }

            }
        }

    }


    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            Drawer3Fragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}