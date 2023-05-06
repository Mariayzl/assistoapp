package com.mariazhang.assistoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageButton
import android.widget.ScrollView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mariazhang.assistoapp.database.anuncio_asistentes
import com.mariazhang.assistoapp.databinding.ActivityCrearAnuncioAsistenteBinding
import com.mariazhang.assistoapp.databinding.ActivityLoginBinding
import java.util.*
import kotlin.collections.HashMap

class CrearAnuncioAsistenteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearAnuncioAsistenteBinding
    private lateinit var authenti: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(
            ActivityCrearAnuncioAsistenteBinding.inflate(layoutInflater).also { binding = it }.root
        )

        authenti = FirebaseAuth.getInstance()


        binding.buttonCrearAsistente.setOnClickListener {

            binding.buttonCrearAsistente.isEnabled = false

            val exp_tipo_discapacidad = listOf(
                binding.check1.isChecked,
                binding.check2.isChecked,
                binding.check3.isChecked,
                binding.check4.isChecked,
                binding.check5.isChecked
            )

            // para pasar el valor de la autenticación de Firebase entre actividades
            val firestore = FirebaseFirestore.getInstance()

            val datos_anuncio_asistente: MutableMap<String, Any> =
                HashMap()

            datos_anuncio_asistente["email"] = authenti.currentUser?.email.toString()
            datos_anuncio_asistente["anuncio_asistente_id"] = UUID.randomUUID().toString()
            datos_anuncio_asistente["enunciado"] = binding.etEnunciadoAsistente.text.toString()
            datos_anuncio_asistente["experiencia "] = binding.etExperienciaAsistente.text.toString()
            datos_anuncio_asistente["exp_tipo_discapacidad"] = exp_tipo_discapacidad
            datos_anuncio_asistente["disponibilidad"] = binding.etHorarioAsistente.text.toString()
            datos_anuncio_asistente["contacto"] = binding.etContactoAsistente.text.toString()
            datos_anuncio_asistente["ciudad"] = binding.etCiudadAsistente.text.toString()
            datos_anuncio_asistente["provincia"] = binding.etProvinciaAsistente.text.toString()

            firestore.collection("anuncio_asistente")
                .add(datos_anuncio_asistente)

        }


    }

}



