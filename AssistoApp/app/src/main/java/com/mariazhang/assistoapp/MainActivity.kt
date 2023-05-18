package com.mariazhang.assistoapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mariazhang.assistoapp.database.anuncio_asistentes
import com.mariazhang.assistoapp.database.anuncio_cuidados
import com.mariazhang.assistoapp.databinding.ActivityMainBinding
import com.mariazhang.assistoapp.interfaces.OnItemClickAsistentes
import com.mariazhang.assistoapp.interfaces.OnItemClickCuidados

class MainActivity : AppCompatActivity(), OnItemClickAsistentes,OnItemClickCuidados {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onBackPressed() {}

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)
        setSupportActionBar(binding.toolbar)

//codigoo 1.

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(

                R.id.drawer1Fragment,
                R.id.drawer2Fragment,
                R.id.drawer3Fragment,
                R.id.drawer4Fragment,
                R.id.drawer5Fragment,
                R.id.perfilActivity

                ),
            binding.drawerLayout
        )
        NavigationUI.setupWithNavController(binding.navView, navController)
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)

    }
    override fun onItemClick(anuncioAsistente: anuncio_asistentes) {

        val sharedPrefs = getSharedPreferences("vistaSuperficial", Context.MODE_PRIVATE)
        val isChecked = sharedPrefs.getBoolean("check", true)

        if (isChecked){
            val intent = Intent(this, VerAnuncioAsistenteActivity::class.java)

            intent.putExtra("ID", anuncioAsistente.anuncio_asistente_id)
            startActivity(intent)
        }else{
            val intent = Intent(this, ModificarAnuncioAsistenteActivity::class.java)

            intent.putExtra("ID", anuncioAsistente.anuncio_asistente_id)
            startActivity(intent)
        }



    }

    override fun onItemClick(anuncioCuidados: anuncio_cuidados) {

        val sharedPrefs = getSharedPreferences("vistaSuperficial", Context.MODE_PRIVATE)
        val isChecked = sharedPrefs.getBoolean("check", true)

        if (isChecked){
            val intent = Intent(this, VerAnuncioCuidadosActivity::class.java)

            intent.putExtra("ID", anuncioCuidados.anuncio_cuidado_id)
            startActivity(intent)

        }else{
            val intent = Intent(this, ModificarAnuncioCuidadosActivity::class.java)

            intent.putExtra("ID", anuncioCuidados.anuncio_cuidado_id)
            startActivity(intent)
        }



    }
}
