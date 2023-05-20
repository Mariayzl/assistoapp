package com.mariazhang.assistoapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.mariazhang.assistoapp.adapters.RecyclerViewAdapterAsistente
import com.mariazhang.assistoapp.database.anuncio_asistentes
import com.mariazhang.assistoapp.interfaces.OnItemClickAsistentes

class BusquedaAvanzAsistenteActivity : AppCompatActivity(), OnItemClickAsistentes {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busqueda_avanz_asistente)

        val firestore = FirebaseFirestore.getInstance()
        val listaAnuncios: MutableList<anuncio_asistentes> = mutableListOf()

        firestore.collection("anuncio_asistente").get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    listaAnuncios.add(anuncio_asistentes.fromJson(document.data))
                }

                val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewFiltroAsistente)
                recyclerView.layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(this@BusquedaAvanzAsistenteActivity)
                    else -> GridLayoutManager(this@BusquedaAvanzAsistenteActivity, columnCount)
                }
                recyclerView.adapter = RecyclerViewAdapterAsistente(listaAnuncios, this@BusquedaAvanzAsistenteActivity)

                println(listaAnuncios.size.toString() + "FORRRR FOOOR FOOOR FOOOR---")
            }
            .addOnFailureListener { exception ->
                Log.i("Weeeeeeeeeeeee", "Error getting documents: ", exception)
            }

        val buttonCrearAsistente = findViewById<Button>(R.id.buttonFiltroCrear1)
        buttonCrearAsistente.setOnClickListener {
            val intent = Intent(this@BusquedaAvanzAsistenteActivity, CrearAnuncioAsistenteActivity::class.java)
            startActivity(intent)
        }

        val buttonFiltroAsistente = findViewById<Button>(R.id.buttonFiltroBusquedaAsistente)
        buttonFiltroAsistente.setOnClickListener {
            val intent = Intent(this@BusquedaAvanzAsistenteActivity, BusquedaAvanzAsistenteActivity::class.java)
            startActivity(intent)
        }

        // Llamar a la función obtenerAnunciosDesdeFirebase con el creador específico
        val creador = "nombre_del_creador"
        val anunciosPorCreador = obtenerAnunciosDesdeFirebase(creador)
        // Utiliza la lista de anuncios obtenidos según el creador como desees
    }

    override fun onItemClick(item: anuncio_asistentes) {
        /*
        // Lógica para manejar el evento de clic en el RecyclerView
        val intent = Intent(this@BusquedaAvanzAsistenteActivity, VerAnunciosAsistenteActivity::class.java)
        intent.putExtra("anuncio", item) // Pasa el objeto anuncio seleccionado a la siguiente actividad
        startActivity(intent)

         */
    }

    // Función para obtener una lista de anuncios desde Firebase Firestore según un creador específico
    private fun obtenerAnunciosDesdeFirebase(creador: String): List<anuncio_asistentes> {
        val anuncios = mutableListOf<anuncio_asistentes>()

        // Obtener la referencia a la colección "anuncio_asistente"
        val anuncioAsistenteRef = FirebaseFirestore.getInstance().collection("anuncio_asistente")

        // Realizar la consulta para obtener los anuncios del creador especificado
        anuncioAsistenteRef.whereEqualTo("Creador", creador).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    anuncios.add(anuncio_asistentes.fromJson(document.data))
                }
                // Aquí puedes utilizar la lista de anuncios obtenidos según el creador como desees
            }
            .addOnFailureListener { exception ->
                Log.e("ObtenerAnuncios", "Error al obtener anuncios: ", exception)
            }

        return anuncios
    }
}