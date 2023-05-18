package com.mariazhang.assistoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mariazhang.assistoapp.adapters.RecyclerViewAdapterCuidados
import com.mariazhang.assistoapp.database.anuncio_cuidados
import com.mariazhang.assistoapp.interfaces.OnItemClickCuidados


class Drawer3Fragment : Fragment() {
    var listener : OnItemClickCuidados? = null

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnItemClickCuidados){
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

                println(listaAnuncios.size.toString() + "FORRRR FOOOR FOOOR FOOOR---")

            }

            .addOnFailureListener { exception ->
                Log.i("Weeeeeeeeeeeee", "Error getting documents: ", exception)
            }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val buttonCrearCuidados = view.findViewById<Button>(R.id.buttonCrear2)

        buttonCrearCuidados.setOnClickListener() {
            val intent = Intent(view.context,CrearAnuncioCuidadosActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            Drawer3Fragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT,columnCount)
                }
            }
    }
}