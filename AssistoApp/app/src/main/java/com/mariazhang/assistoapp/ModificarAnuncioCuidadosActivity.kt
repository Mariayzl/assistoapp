package com.mariazhang.assistoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.mariazhang.assistoapp.database.anuncio_cuidados
import com.mariazhang.assistoapp.databinding.ActivityCrearAnuncioCuidadosBinding
import com.mariazhang.assistoapp.databinding.ActivityModificarAnuncioCuidadosBinding
import java.util.HashMap

class ModificarAnuncioCuidadosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModificarAnuncioCuidadosBinding

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(
            ActivityModificarAnuncioCuidadosBinding.inflate(layoutInflater)
                .also { binding = it }.root
        )

        cargar()

        binding.buttonModificarCuidados.setOnClickListener {

            binding.buttonModificarCuidados.isEnabled = false
            binding.buttonBorrarCuidados.isEnabled = false
            binding.buttonRestablecerCuidados.isEnabled = false

            val id = intent.getStringExtra("ID")
            val firestore = FirebaseFirestore.getInstance()

            firestore.collection("anuncio_cuidados").whereEqualTo("anuncio_cuidados_id", id).get()
                .addOnSuccessListener { documents ->

                    val anuncioCuidadoMapa: MutableMap<String, Any> = HashMap()

                    for (document in documents) {


                        val tipo_discapacidad = listOf(
                            binding.check1.isChecked,
                            binding.check2.isChecked,
                            binding.check3.isChecked,
                            binding.check4.isChecked,
                            binding.check5.isChecked
                        )

                        val discapacidades = listOf(
                            binding.checkDiscapacidad1.isChecked,
                            binding.checkDiscapacidad2.isChecked,
                            binding.checkDiscapacidad3.isChecked,
                            binding.checkDiscapacidad4.isChecked,
                            binding.checkDiscapacidad5.isChecked,
                            binding.checkDiscapacidad6.isChecked,
                            binding.checkDiscapacidad7.isChecked,
                            binding.checkDiscapacidad8.isChecked,
                        )

                        val necesidades = listOf(
                            binding.checkNecesidad1.isChecked,
                            binding.checkNecesidad2.isChecked,
                            binding.checkNecesidad3.isChecked,
                            binding.checkNecesidad4.isChecked,
                            binding.checkNecesidad5.isChecked,
                            binding.checkNecesidad6.isChecked,
                            binding.checkNecesidad7.isChecked,
                            binding.checkNecesidad8.isChecked,
                        )

                        anuncioCuidadoMapa["anuncio_asistente_id"] = id.toString()
                        anuncioCuidadoMapa["email"] = document.data["email"].toString()
                        anuncioCuidadoMapa["enunciado"] = binding.etEnunciadoCuidados.text.toString()
                        anuncioCuidadoMapa["grado_discapacidad"] = binding.etGradodiscapacidad.text.toString()
                        anuncioCuidadoMapa["tipo_discapacidad"] = tipo_discapacidad
                        anuncioCuidadoMapa["salario"] = binding.etSalarioCuidados.text.toString()
                        anuncioCuidadoMapa["contacto"] = binding.etContactoCuidados.text.toString()
                        anuncioCuidadoMapa["ciudad"] = binding.etCiudadCuidados.text.toString()
                        anuncioCuidadoMapa["descripcion"] = binding.etDescripcion.text.toString()
                        anuncioCuidadoMapa["provincia"] = binding.etProvinciaCuidados.text.toString()
                        anuncioCuidadoMapa["discapacidades"] = discapacidades
                        anuncioCuidadoMapa["necesidades"] = necesidades

                        val document = documents.documents[0]
                        document.reference.update(anuncioCuidadoMapa)

                        binding.buttonBorrarCuidados.isEnabled = true
                        binding.buttonModificarCuidados.isEnabled = true
                        binding.buttonRestablecerCuidados.isEnabled = true

                    }

                }

                .addOnFailureListener { exception ->
                    Log.i("Weeeeeeeeeeeee", "Error getting documents: ", exception)
                }

        }

        binding.buttonRestablecerCuidados.setOnClickListener {

            binding.buttonBorrarCuidados.isEnabled = false
            binding.buttonModificarCuidados.isEnabled = false
            binding.buttonRestablecerCuidados.isEnabled = false

            cargar()

            binding.buttonBorrarCuidados.isEnabled = true
            binding.buttonModificarCuidados.isEnabled = true
            binding.buttonRestablecerCuidados.isEnabled = true

        }

        binding.buttonBorrarCuidados.setOnClickListener {


            binding.buttonBorrarCuidados.isEnabled = false
            binding.buttonModificarCuidados.isEnabled = false
            binding.buttonRestablecerCuidados.isEnabled = false

            val id = intent.getStringExtra("ID")
            val firestore = FirebaseFirestore.getInstance()

            firestore.collection("anuncio_cuidados").whereEqualTo("anuncio_cuidados_id", id).get()
                .addOnSuccessListener { documents ->

                    for (document in documents) {

                        val document = documents.documents[0]
                        document.reference.delete()

                        binding.buttonBorrarCuidados.isEnabled = true
                        binding.buttonModificarCuidados.isEnabled = true
                        binding.buttonRestablecerCuidados.isEnabled = true

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

        firestore.collection("anuncio_cuidados").whereEqualTo("anuncio_cuidados_id", id).get()
            .addOnSuccessListener { documents ->

                print(documents.size())
                for (document in documents) {

                    val anuncioCuidados = anuncio_cuidados.fromJson(document.data)

                    binding.etEnunciadoCuidados.setText(anuncioCuidados.enunciado)
                    binding.etDescripcion.setText(anuncioCuidados.descripcion)

                    binding.check1.isChecked = anuncioCuidados.tipo_discapacidad[0]
                    binding.check2.isChecked = anuncioCuidados.tipo_discapacidad[1]
                    binding.check3.isChecked = anuncioCuidados.tipo_discapacidad[2]
                    binding.check4.isChecked = anuncioCuidados.tipo_discapacidad[3]
                    binding.check5.isChecked = anuncioCuidados.tipo_discapacidad[4]

                    binding.checkDiscapacidad1.isChecked = anuncioCuidados.discapacidades[0]
                    binding.checkDiscapacidad2.isChecked = anuncioCuidados.discapacidades[1]
                    binding.checkDiscapacidad3.isChecked = anuncioCuidados.discapacidades[2]
                    binding.checkDiscapacidad4.isChecked = anuncioCuidados.discapacidades[3]
                    binding.checkDiscapacidad5.isChecked = anuncioCuidados.discapacidades[4]
                    binding.checkDiscapacidad6.isChecked = anuncioCuidados.discapacidades[5]
                    binding.checkDiscapacidad7.isChecked = anuncioCuidados.discapacidades[6]
                    binding.checkDiscapacidad8.isChecked = anuncioCuidados.discapacidades[7]

                    binding.checkNecesidad1.isChecked = anuncioCuidados.necesidades[0]
                    binding.checkNecesidad2.isChecked = anuncioCuidados.necesidades[1]
                    binding.checkNecesidad3.isChecked = anuncioCuidados.necesidades[2]
                    binding.checkNecesidad4.isChecked = anuncioCuidados.necesidades[3]
                    binding.checkNecesidad5.isChecked = anuncioCuidados.necesidades[4]
                    binding.checkNecesidad6.isChecked = anuncioCuidados.necesidades[5]
                    binding.checkNecesidad7.isChecked = anuncioCuidados.necesidades[6]
                    binding.checkNecesidad8.isChecked = anuncioCuidados.necesidades[7]

                    binding.etSalarioCuidados.setText(anuncioCuidados.salario)
                    binding.etSalarioCuidados.setText(anuncioCuidados.tipo_jornada)
                    binding.etContactoCuidados.setText(anuncioCuidados.contacto)
                    binding.etCiudadCuidados.setText(anuncioCuidados.ciudad)
                    binding.etGradodiscapacidad.setText(anuncioCuidados.grado_discapacidad)
                    binding.etProvinciaCuidados.setText(anuncioCuidados.provincia)

                }

            }

            .addOnFailureListener { exception ->
                Log.i("Weeeeeeeeeeeee", "Error getting documents: ", exception)
            }
    }
}