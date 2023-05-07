package com.mariazhang.assistoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.mariazhang.assistoapp.databinding.ActivityCrearAnuncioAsistenteBinding
import com.mariazhang.assistoapp.databinding.ActivityCrearAnuncioCuidadosBinding

class CrearAnuncioCuidadosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearAnuncioCuidadosBinding
    private lateinit var authenti: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(
        ActivityCrearAnuncioCuidadosBinding.inflate(layoutInflater).also { binding = it }.root
        )

        authenti = FirebaseAuth.getInstance()

        binding.buttonCancelarCuidados.setOnClickListener{




        }

    }


}