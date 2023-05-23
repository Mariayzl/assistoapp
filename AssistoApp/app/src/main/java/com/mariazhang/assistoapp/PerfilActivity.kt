package com.mariazhang.assistoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mariazhang.assistoapp.databinding.ActivityPerfilBinding

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding
    val user = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            ActivityPerfilBinding.inflate(layoutInflater).also { binding = it }.root
        )

        binding.etEmail.setText(user?.email.toString())

        binding.buttonModificarPerfil.setOnClickListener(){
            if (binding.etpassword.text.isNotEmpty()) {
                val newPassword = binding.etpassword.text.toString()
                user?.updatePassword(newPassword)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "La contraseña se actualizó exitosamente",
                                Toast.LENGTH_SHORT
                            ).show()
                            Thread.sleep(1000)
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Ocurrió un error al actualizar la contraseña",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }else{
                Toast.makeText(
                    applicationContext,
                    "La nueva contraseña no puede estar vacia",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.buttonEliminarPerfil.setOnClickListener(){

            user?.delete()
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "La cuenta se eliminó correctamente",
                            Toast.LENGTH_SHORT
                        ).show()
                        Thread.sleep(1000)
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Ocurrió un error al eliminar la cuenta",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }



}




}