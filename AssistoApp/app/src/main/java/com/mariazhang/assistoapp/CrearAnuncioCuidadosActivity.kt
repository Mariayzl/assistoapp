package com.mariazhang.assistoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mariazhang.assistoapp.database.anuncio_cuidados
import com.mariazhang.assistoapp.databinding.ActivityCrearAnuncioAsistenteBinding
import com.mariazhang.assistoapp.databinding.ActivityCrearAnuncioCuidadosBinding
import java.util.*
import kotlin.collections.HashMap

class CrearAnuncioCuidadosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearAnuncioCuidadosBinding
    private lateinit var authenti: FirebaseAuth

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(
        ActivityCrearAnuncioCuidadosBinding.inflate(layoutInflater).also { binding = it }.root
        )

        authenti = FirebaseAuth.getInstance()

        binding.buttonCrearCuidados.setOnClickListener{
            if (binding.etEnunciadoCuidados.text.isNotEmpty() &&
                binding.etDescripcion.text.isNotEmpty() &&
                binding.etGradodiscapacidad.text.isNotEmpty() &&
                binding.etJornadaCuidados.text.isNotEmpty() &&
                binding.etSalarioCuidados.text.isNotEmpty() &&
                binding.etContactoCuidados.text.isNotEmpty() &&
                binding.etCiudadCuidados.text.isNotEmpty() &&
                binding.etProvinciaCuidados.text.isNotEmpty()
            ) {
                binding.buttonCrearCuidados.isEnabled = false
                binding.buttonRestablecerCuidados.isEnabled = false

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
                    binding.checkDiscapacidad8.isChecked
                )

                val necesidades = listOf(
                    binding.checkNecesidad1.isChecked,
                    binding.checkNecesidad2.isChecked,
                    binding.checkNecesidad3.isChecked,
                    binding.checkNecesidad4.isChecked,
                    binding.checkNecesidad5.isChecked,
                    binding.checkNecesidad6.isChecked,
                    binding.checkNecesidad7.isChecked,
                    binding.checkNecesidad8.isChecked
                )

                // para pasar el valor de la autenticación de Firebase entre actividades
                val firestore = FirebaseFirestore.getInstance()

                val datos_anuncio_cuidados: MutableMap<String, Any> =
                    HashMap()

                datos_anuncio_cuidados["mail"] = authenti.currentUser?.email.toString()
                datos_anuncio_cuidados["anuncio_cuidados_id"] = UUID.randomUUID().toString()
                datos_anuncio_cuidados["enunciado"] = binding.etEnunciadoCuidados.text.toString()
                datos_anuncio_cuidados["descripcion"] = binding.etDescripcion.text.toString()
                datos_anuncio_cuidados["grado_discapacidad"] = binding.etGradodiscapacidad.text.toString()
                datos_anuncio_cuidados["tipo_discapacidad"] = tipo_discapacidad
                datos_anuncio_cuidados["discapacidades"] = discapacidades
                datos_anuncio_cuidados["necesidades"] = necesidades
                datos_anuncio_cuidados["tipo_jornada"] = binding.etJornadaCuidados.text.toString()
                datos_anuncio_cuidados["salario"] = binding.etSalarioCuidados.text.toString()
                datos_anuncio_cuidados["contacto"] = binding.etContactoCuidados.text.toString()
                datos_anuncio_cuidados["ciudad"] = binding.etCiudadCuidados.text.toString()
                datos_anuncio_cuidados["provincia"] = binding.etProvinciaCuidados.text.toString()

                firestore.collection("anuncio_cuidados")
                    .add(datos_anuncio_cuidados)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                binding.buttonCrearCuidados.isEnabled = true
                binding.buttonRestablecerCuidados.isEnabled = true

                Toast.makeText(
                    applicationContext,
                    "Anuncio cuidados creado con éxito",
                    Toast.LENGTH_SHORT
                ).show()

            }else{
                Toast.makeText(applicationContext, "Faltan campos por rellenar", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonRestablecerCuidados.setOnClickListener {
            restablecer()
        }

    }

    private fun restablecer() {

                    binding.etEnunciadoCuidados.setText("")
                    binding.etDescripcion.setText("")

                    binding.check1.isChecked = false
                    binding.check2.isChecked = false
                    binding.check3.isChecked = false
                    binding.check4.isChecked = false
                    binding.check5.isChecked = false

                    binding.checkDiscapacidad1.isChecked = false
                    binding.checkDiscapacidad2.isChecked = false
                    binding.checkDiscapacidad3.isChecked = false
                    binding.checkDiscapacidad4.isChecked = false
                    binding.checkDiscapacidad5.isChecked = false
                    binding.checkDiscapacidad6.isChecked = false
                    binding.checkDiscapacidad7.isChecked = false
                    binding.checkDiscapacidad8.isChecked = false

                    binding.checkNecesidad1.isChecked = false
                    binding.checkNecesidad2.isChecked = false
                    binding.checkNecesidad3.isChecked = false
                    binding.checkNecesidad4.isChecked = false
                    binding.checkNecesidad5.isChecked = false
                    binding.checkNecesidad6.isChecked = false
                    binding.checkNecesidad7.isChecked = false
                    binding.checkNecesidad8.isChecked = false

                    binding.etSalarioCuidados.setText("")
                    binding.etSalarioCuidados.setText("")
                    binding.etContactoCuidados.setText("")
                    binding.etCiudadCuidados.setText("")
                    binding.etGradodiscapacidad.setText("")
                    binding.etProvinciaCuidados.setText("")



    }


}