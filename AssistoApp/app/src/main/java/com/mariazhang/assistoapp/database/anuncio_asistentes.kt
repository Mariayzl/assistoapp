package com.mariazhang.assistoapp.database

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Objects


class anuncio_asistentes(

    var anuncio_asistente_id: String,
    var enunciado: String?,
    var experiencia: String?,
    var exp_tipo_discapacidad: List<Boolean>,
    var disponibilidad: String?,
    var referencias: String?,
    var habilidades: String?,
    var salario: String?,
    var contacto: String?,
    var ciudad: String?,
    var provincia: String?
) {

    companion object {

        fun getAnuncios(): MutableList<anuncio_asistentes> {

            val authenti = FirebaseAuth.getInstance()
            val user = authenti.currentUser!!

            val firestore = FirebaseFirestore.getInstance()

            var listaAnuncios: MutableList<anuncio_asistentes> = mutableListOf()

            firestore.collection("anuncio_asistentes").whereEqualTo("email", user.email).get()
                .addOnSuccessListener { documents ->

                    for (document in documents) {

                        var anuncio =

                            listaAnuncios.add(fromJson(document.data))
                    }

                }

                .addOnFailureListener { exception ->
                    Log.i("MANOLOOo", "Error getting documents: ", exception)
                }

            return listaAnuncios
        }

        fun fromJson(anuncioMapa: MutableMap<String, Any>): anuncio_asistentes {

            var lista = anuncioMapa["exp_tipo_discapacidad"] as List<Boolean>

            return anuncio_asistentes(
                anuncioMapa["anuncio_asistente_id"].toString(),
                anuncioMapa["enunciado"].toString(),
                anuncioMapa["experiencia"].toString(),
                lista ,
                anuncioMapa["disponibilidad"].toString(),
                anuncioMapa["referencias"].toString(),
                anuncioMapa["habilidades"].toString(),
                anuncioMapa["salario"].toString(),
                anuncioMapa["contacto"].toString(),
                anuncioMapa["ciudad"].toString(),
                anuncioMapa["provincia"].toString()
            )
        }

    }

}



