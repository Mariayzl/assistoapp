package com.mariazhang.assistoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.mariazhang.assistoapp.database.anuncio_cuidados
import com.mariazhang.assistoapp.databinding.ActivityVerAnuncioCuidadosBinding
import java.util.HashMap

class VerAnuncioCuidadosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVerAnuncioCuidadosBinding

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(
            ActivityVerAnuncioCuidadosBinding.inflate(layoutInflater)
                .also { binding = it }.root
        )

        cargar()

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