package com.mariazhang.assistoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.mariazhang.assistoapp.database.anuncio_asistentes
import com.mariazhang.assistoapp.databinding.ActivityCrearAnuncioAsistenteBinding
import com.mariazhang.assistoapp.databinding.ActivityModificarAnuncioAsistenteBinding

class ModificarAnuncioAsistenteActivity : AppCompatActivity() {


    private lateinit var binding: ActivityModificarAnuncioAsistenteBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(
            ActivityModificarAnuncioAsistenteBinding.inflate(layoutInflater).also { binding = it }.root
        )

        val anuncioAsis = intent.getParcelableExtra<anuncio_asistentes>("anuncioAsistente")

            binding.etEnunciadoAsistente.setText(anuncioAsis!!.enunciado)
            binding.etExperienciaAsistente.setText(anuncioAsis.experiencia)
            binding.check1.isChecked=anuncioAsis.exp_tipo_discapacidad[0]
            binding.check2.isChecked=anuncioAsis.exp_tipo_discapacidad[1]
            binding.check3.isChecked=anuncioAsis.exp_tipo_discapacidad[2]
            binding.check4.isChecked=anuncioAsis.exp_tipo_discapacidad[3]
            binding.check5.isChecked=anuncioAsis.exp_tipo_discapacidad[4]
            binding.etHorarioAsistente.setText(anuncioAsis.disponibilidad)
            binding.etReferenciaslaborales.setText(anuncioAsis.habilidades)
            binding.etSalarioAsistente.setText(anuncioAsis.salario)
            binding.etContactoAsistente.setText(anuncioAsis.contacto)
            binding.etCiudadAsistente.setText(anuncioAsis.ciudad)
            binding.etProvinciaAsistente.setText(anuncioAsis.provincia)


        binding.buttonModificarAsistente.setOnClickListener{

            binding.buttonModificarAsistente.isEnabled = false



        }


    }




}