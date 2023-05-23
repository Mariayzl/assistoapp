package com.mariazhang.assistoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mariazhang.assistoapp.database.anuncio_asistentes
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
            if (binding.etEnunciadoAsistente.text.isNotEmpty() &&
                binding.etExperienciaAsistente.text.isNotEmpty() &&
                binding.etHorarioAsistente.text.isNotEmpty() &&
                binding.etHabilidadesAsistente.text.isNotEmpty() &&
                binding.etSalarioAsistente.text.isNotEmpty() &&
                binding.etContactoAsistente.text.isNotEmpty() &&
                binding.etCiudadAsistente.text.isNotEmpty() &&
                binding.etProvinciaAsistente.text.isNotEmpty()
            ) {

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

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                binding.buttonCrearAsistente.isEnabled = true
                binding.buttonRestablecerAsistente.isEnabled = true

                Toast.makeText(
                    applicationContext,
                    "Anuncio asistente creado con éxito",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                Toast.makeText(applicationContext, "Faltan campos por rellenar", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonRestablecerAsistente.setOnClickListener {
            restablecer()
            Toast.makeText(applicationContext, "Reestableciendo datos", Toast.LENGTH_SHORT).show()
        }


    }

    private fun cargar() {
        val id = intent.getStringExtra("ID")
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("anuncio_asistente").whereEqualTo("anuncio_asistente_id", id).get()
            .addOnSuccessListener { documents ->

                for (document in documents) {

                    var anuncioAsis = anuncio_asistentes.fromJson(document.data)

                    binding.etEnunciadoAsistente.setText(anuncioAsis!!.enunciado)
                    binding.etExperienciaAsistente.setText(anuncioAsis.experiencia)
                    binding.check1.isChecked = anuncioAsis.exp_tipo_discapacidad[0]
                    binding.check2.isChecked = anuncioAsis.exp_tipo_discapacidad[1]
                    binding.check3.isChecked = anuncioAsis.exp_tipo_discapacidad[2]
                    binding.check4.isChecked = anuncioAsis.exp_tipo_discapacidad[3]
                    binding.check5.isChecked = anuncioAsis.exp_tipo_discapacidad[4]
                    binding.etHorarioAsistente.setText(anuncioAsis.disponibilidad)
                    binding.etHabilidadesAsistente.setText(anuncioAsis.habilidades)
                    binding.etSalarioAsistente.setText(anuncioAsis.salario)
                    binding.etContactoAsistente.setText(anuncioAsis.contacto)
                    binding.etCiudadAsistente.setText(anuncioAsis.ciudad)
                    binding.etProvinciaAsistente.setText(anuncioAsis.provincia)

                }

            }

            .addOnFailureListener { exception ->
                Log.i("Weeeeeeeeeeeee", "Error getting documents: ", exception)
            }
    }

    private fun restablecer() {

        binding.etEnunciadoAsistente.setText("")
        binding.etExperienciaAsistente.setText("")
        binding.check1.isChecked = false
        binding.check2.isChecked = false
        binding.check3.isChecked = false
        binding.check4.isChecked = false
        binding.check5.isChecked = false
        binding.etHorarioAsistente.setText("")
        binding.etHabilidadesAsistente.setText("")
        binding.etSalarioAsistente.setText("")
        binding.etContactoAsistente.setText("")
        binding.etCiudadAsistente.setText("")
        binding.etProvinciaAsistente.setText("")

    }

}






