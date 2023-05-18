package com.mariazhang.assistoapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mariazhang.assistoapp.databinding.ActivityAyudaBinding
import com.mariazhang.assistoapp.databinding.ActivityLoginBinding

class AyudaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAyudaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityAyudaBinding.inflate(layoutInflater).also { binding = it }.root)

        //Botón Volver
        binding.buttonVolverAyuda.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        //Botón asistente de correo electrónico
        binding.buttonCorreoAyuda.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:")
            startActivity(Intent.createChooser(intent, "Enviar correo electrónico"))
        }

    }

}