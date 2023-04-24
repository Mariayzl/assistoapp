package com.mariazhang.assistoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mariazhang.assistoapp.databinding.ActivityLoginBinding
import com.mariazhang.assistoapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var authenti: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(ActivityRegisterBinding.inflate(layoutInflater).also { binding = it }.root)

        authenti = FirebaseAuth.getInstance()

        binding.buttonCrear.setOnClickListener {

            val email = binding.etEmail.text.toString()
            val password = binding.etpassword.text.toString()

                    authenti.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                authenti.signInWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(this) { task ->
                                        if (task.isSuccessful) {
                                            val intent = Intent(this, MainActivity::class.java)
                                            startActivity(intent)
                                        } else {
                                            Toast.makeText(
                                                this,
                                                "Ha habido un problema conectando al servidor",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                            } else {
                                //
                                Toast.makeText(
                                    this,
                                    "Error en el formato del correo o contraseña invalida" ,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }

        }

    }
