package com.mariazhang.assistoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mariazhang.assistoapp.databinding.ActivityIniciarcuentaBinding
import com.mariazhang.assistoapp.databinding.ActivityLoginBinding

class IniciarcuentaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIniciarcuentaBinding
    private lateinit var authenti: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            ActivityIniciarcuentaBinding.inflate(layoutInflater).also { binding = it }.root
        )
        authenti = FirebaseAuth.getInstance()




        binding.buttonIniciar.setOnClickListener {
            if (binding.etpassword.text.isNotEmpty()) {
                binding.buttonIniciar.isEnabled = false
                var email = binding.etmail.text.toString()
                var password = binding.etpassword.text.toString()

                authenti.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            print(task)
                            Log.i("-----Proceso Iniciar Cuenta ejecutando-----", task.toString())
                            var usuariologin = this?.getSharedPreferences("login", MODE_PRIVATE)
                            val intent = Intent(this, MainActivity::class.java)

                            // pasamos el valor de la autenticación de Firebase entre actividades con un Bundle en el Intent
                            intent.putExtra("email", email)
                            intent.putExtra("password", password)
                            startActivity(intent)
                            Toast.makeText(
                                applicationContext,
                                "Credenciales correctas, iniciando sesión",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {
                            Toast.makeText(
                                this,
                                "Ha habido un problema conectando al servidor",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                binding.buttonIniciar.isEnabled = true
            }else{
                Toast.makeText(
                    applicationContext,
                    "La nueva contraseña no puede estar vacia",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}