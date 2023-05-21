package com.mariazhang.assistoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.mariazhang.assistoapp.database.anuncio_asistentes
import com.mariazhang.assistoapp.databinding.ActivityVerAnuncioAsistenteBinding
import java.util.HashMap

class VerAnuncioAsistenteActivity : AppCompatActivity() {

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private lateinit var binding: ActivityVerAnuncioAsistenteBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(
            ActivityVerAnuncioAsistenteBinding.inflate(layoutInflater)
                .also { binding = it }.root
        )

        cargar()

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

}