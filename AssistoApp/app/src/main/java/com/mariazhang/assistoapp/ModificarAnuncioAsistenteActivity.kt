package com.mariazhang.assistoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.mariazhang.assistoapp.database.anuncio_asistentes
import com.mariazhang.assistoapp.databinding.ActivityModificarAnuncioAsistenteBinding
import java.util.HashMap

class ModificarAnuncioAsistenteActivity : AppCompatActivity() {


    private lateinit var binding: ActivityModificarAnuncioAsistenteBinding

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(
            ActivityModificarAnuncioAsistenteBinding.inflate(layoutInflater)
                .also { binding = it }.root
        )

        cargar()

        binding.buttonModificarAsistente.setOnClickListener {
            if (binding.etEnunciadoAsistente.text.isNotEmpty() &&
                binding.etExperienciaAsistente.text.isNotEmpty() &&
                binding.etHorarioAsistente.text.isNotEmpty() &&
                binding.etHabilidadesAsistente.text.isNotEmpty() &&
                binding.etSalarioAsistente.text.isNotEmpty() &&
                binding.etContactoAsistente.text.isNotEmpty() &&
                binding.etCiudadAsistente.text.isNotEmpty() &&
                binding.etProvinciaAsistente.text.isNotEmpty()
            ) {
                binding.buttonModificarAsistente.isEnabled = false
                binding.buttonRestablecerAsistente.isEnabled = false
                binding.buttonEliminarAsistente.isEnabled = false

                val id = intent.getStringExtra("ID")
                val firestore = FirebaseFirestore.getInstance()

                firestore.collection("anuncio_asistente").whereEqualTo("anuncio_asistente_id", id)
                    .get()
                    .addOnSuccessListener { documents ->

                        val anuncioMapa: MutableMap<String, Any> = HashMap()



                        for (document in documents) {


                            val tipo_discapacidad = listOf(
                                binding.check1.isChecked,
                                binding.check2.isChecked,
                                binding.check3.isChecked,
                                binding.check4.isChecked,
                                binding.check5.isChecked
                            )

                            anuncioMapa["anuncio_asistente_id"] = id.toString()
                            anuncioMapa["email"] = document.data["email"].toString()
                            anuncioMapa["enunciado"] = binding.etEnunciadoAsistente.text.toString()
                            anuncioMapa["experiencia"] =
                                binding.etExperienciaAsistente.text.toString()
                            anuncioMapa["exp_tipo_discapacidad"] = tipo_discapacidad
                            anuncioMapa["disponibilidad"] =
                                binding.etHorarioAsistente.text.toString()
                            anuncioMapa["habilidades"] =
                                binding.etHabilidadesAsistente.text.toString()
                            anuncioMapa["salario"] = binding.etSalarioAsistente.text.toString()
                            anuncioMapa["contacto"] = binding.etContactoAsistente.text.toString()
                            anuncioMapa["ciudad"] = binding.etCiudadAsistente.text.toString()
                            anuncioMapa["provincia"] = binding.etProvinciaAsistente.text.toString()

                            val document = documents.documents[0]
                            document.reference.update(anuncioMapa)

                            binding.buttonModificarAsistente.isEnabled = true
                            binding.buttonRestablecerAsistente.isEnabled = true
                            binding.buttonEliminarAsistente.isEnabled = true

                        }

                    }

                    .addOnFailureListener { exception ->
                        Log.i("Weeeeeeeeeeeee", "Error getting documents: ", exception)
                    }
            }else{
                Toast.makeText(applicationContext, "Faltan campos por rellenar", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonRestablecerAsistente.setOnClickListener {

            binding.buttonModificarAsistente.isEnabled = false
            binding.buttonRestablecerAsistente.isEnabled = false
            binding.buttonEliminarAsistente.isEnabled = false

            cargar()

            binding.buttonModificarAsistente.isEnabled = true
            binding.buttonRestablecerAsistente.isEnabled = true
            binding.buttonEliminarAsistente.isEnabled = true

        }

        binding.buttonEliminarAsistente.setOnClickListener {


            binding.buttonModificarAsistente.isEnabled = false
            binding.buttonRestablecerAsistente.isEnabled = false
            binding.buttonEliminarAsistente.isEnabled = false

            val id = intent.getStringExtra("ID")
            val firestore = FirebaseFirestore.getInstance()

            firestore.collection("anuncio_asistente").whereEqualTo("anuncio_asistente_id", id).get()
                .addOnSuccessListener { documents ->

                    for (document in documents) {

                        val document = documents.documents[0]
                        document.reference.delete()

                        binding.buttonModificarAsistente.isEnabled = true
                        binding.buttonRestablecerAsistente.isEnabled = true
                        binding.buttonEliminarAsistente.isEnabled = true

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    }

                }

                .addOnFailureListener { exception ->
                    Log.i("Weeeeeeeeeeeee", "Error getting documents: ", exception)
                }

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


}