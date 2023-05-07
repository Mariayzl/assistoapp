package com.mariazhang.assistoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.mariazhang.assistoapp.databinding.ActivityCrearAnuncioAsistenteBinding
import com.mariazhang.assistoapp.databinding.ActivityModificarAnuncioAsistenteBinding

class ModificarAnuncioAsistenteActivity : AppCompatActivity() {



    private lateinit var binding: ActivityModificarAnuncioAsistenteBinding
    private lateinit var authenti: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            ActivityModificarAnuncioAsistenteBinding.inflate(layoutInflater).also { binding = it }.root
        )

        authenti = FirebaseAuth.getInstance()

        binding.buttonModificarAsistente.setOnClickListener{

            binding.buttonModificarAsistente.isEnabled = false




        }


    }




}