package com.mariazhang.assistoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mariazhang.assistoapp.databinding.ActivityCrearAnuncioAsistenteBinding
import java.util.*
import kotlin.collections.HashMap

class CrearAnuncioAsistenteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearAnuncioAsistenteBinding
    private lateinit var authenti: FirebaseAuth

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(
            ActivityCrearAnuncioAsistenteBinding.inflate(layoutInflater).also { binding = it }.root
        )

        authenti = FirebaseAuth.getInstance()


        binding.buttonCrearAsistente.setOnClickListener {

            binding.buttonCrearAsistente.isEnabled = false
            binding.buttonRestablecerAsistente.isEnabled = false

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
            datos_anuncio_asistente["experiencia"] = binding.etExperienciaAsistente.text.toString()
            datos_anuncio_asistente["exp_tipo_discapacidad"] = exp_tipo_discapacidad
            datos_anuncio_asistente["disponibilidad"] = binding.etHorarioAsistente.text.toString()
            datos_anuncio_asistente["habilidades"] = binding.etHabilidadesAsistente.text.toString()
            datos_anuncio_asistente["salario"] = binding.etSalarioAsistente.text.toString()
            datos_anuncio_asistente["contacto"] = binding.etContactoAsistente.text.toString()
            datos_anuncio_asistente["ciudad"] = binding.etCiudadAsistente.text.toString()
            datos_anuncio_asistente["provincia"] = binding.etProvinciaAsistente.text.toString()

            firestore.collection("anuncio_asistente")
                .add(datos_anuncio_asistente)

            binding.buttonCrearAsistente.isEnabled = true
            binding.buttonRestablecerAsistente.isEnabled = true

        }


    }

}



