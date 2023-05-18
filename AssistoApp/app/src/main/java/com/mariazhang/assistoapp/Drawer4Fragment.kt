package com.mariazhang.assistoapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mariazhang.assistoapp.adapters.RecyclerViewAdapterAsistente
import com.mariazhang.assistoapp.database.anuncio_asistentes
import com.mariazhang.assistoapp.interfaces.OnItemClickAsistentes


class Drawer4Fragment : Fragment(){

   var listener : OnItemClickAsistentes ? = null

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnItemClickAsistentes ){
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
        ?.putBoolean("check", false)
        ?.apply()

    val view = inflater.inflate(R.layout.fragment_drawer4, container, false)

    val authenti = FirebaseAuth.getInstance()
    val user = authenti.currentUser!!

    val firestore = FirebaseFirestore.getInstance()

    var listaAnuncios: MutableList<anuncio_asistentes> = mutableListOf()

    firestore.collection("anuncio_asistente").whereEqualTo("email", user.email).get()
        .addOnSuccessListener { documents ->

            for (document in documents) {

                listaAnuncios.add(anuncio_asistentes.fromJson(document.data))

            }

            val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerviewMisanuncios)

            recyclerView.layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }

            recyclerView.adapter = RecyclerViewAdapterAsistente(listaAnuncios, listener)

            println(listaAnuncios.size.toString() + "FORRRR FOOOR FOOOR FOOOR---")

        }

        .addOnFailureListener { exception ->
            Log.i("Weeeeeeeeeeeee", "Error getting documents: ", exception)
        }

    return view
}


    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            Drawer4Fragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT,columnCount)
                }
            }
    }


}