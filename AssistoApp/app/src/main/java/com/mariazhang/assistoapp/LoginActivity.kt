package com.mariazhang.assistoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.GsonBuilder
import com.mariazhang.assistoapp.databinding.ActivityLoginBinding
import java.io.OutputStream

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authenti: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        setContentView(ActivityLoginBinding.inflate(layoutInflater).also { binding = it }.root)


        binding.bLoginIniciar.setOnClickListener {
            val intent = Intent(this, IniciarcuentaActivity::class.java)
            startActivity(intent)


        }

        binding.bLoginCuenta.setOnClickListener {

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }
    }


}