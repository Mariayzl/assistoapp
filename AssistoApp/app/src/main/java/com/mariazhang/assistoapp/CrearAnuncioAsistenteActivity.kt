package com.mariazhang.assistoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.ScrollView
import com.google.firebase.auth.FirebaseAuth
import com.mariazhang.assistoapp.databinding.ActivityCrearAnuncioAsistenteBinding
import com.mariazhang.assistoapp.databinding.ActivityLoginBinding

class CrearAnuncioAsistenteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearAnuncioAsistenteBinding
 //   private lateinit var authenti: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityCrearAnuncioAsistenteBinding.inflate(layoutInflater).also { binding = it }.root)

  //      authenti = FirebaseAuth.getInstance()

/*
        binding.buttonCrearAsistente.setOnClickListener {
            val intent = Intent(this, CrearAnuncioCuidadosActivity::class.java)
            startActivity(intent)
        }
*/
    }
}
