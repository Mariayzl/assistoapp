package com.mariazhang.assistoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.ScrollView
import com.google.firebase.auth.FirebaseAuth
import com.mariazhang.assistoapp.databinding.ActivityLoginBinding
import com.mariazhang.assistoapp.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authenti: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(ActivityLoginBinding.inflate(layoutInflater).also { binding = it }.root)

        authenti = FirebaseAuth.getInstance()

        binding.bLoginIniciar.setOnClickListener {
            val intent = Intent(this, IniciarcuentaActivity::class.java)
            startActivity(intent)
        }

        binding.bLoginCuenta.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.bLoginAyuda.setOnClickListener {
            val intent = Intent(this, CrearAnuncioAsistenteActivity::class.java)
            startActivity(intent)
        }


    }
}
