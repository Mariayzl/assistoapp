package com.mariazhang.assistoapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
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
import com.mariazhang.assistoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        val screenSplash = installSplashScreen()

        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)
        setSupportActionBar(binding.toolbar)

//codigoo 1

/*
        val db = Firebase.firestore
        db.collection("anuncio_asistente")

            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.i("MANOLOO", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.i("MANOLOOo", "Error getting documents: ", exception)
            }
*/

//codigoo 1.

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(

                R.id.drawer1Fragment,
                R.id.drawer2Fragment,
                R.id.drawer3Fragment,
                R.id.drawer4Fragment,
                R.id.perfilActivity

                ),
            binding.drawerLayout
        )
        NavigationUI.setupWithNavController(binding.navView, navController)
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)

        Thread.sleep(1000)
        screenSplash.setKeepOnScreenCondition { false }

        screenSplash.setOnExitAnimationListener { splashScreenView ->
            val slideBack = ObjectAnimator.ofFloat(
                splashScreenView.view,
                View.TRANSLATION_Y,
                0f,
                splashScreenView.view.width.toFloat()
            ).apply {
                interpolator = DecelerateInterpolator()
                duration = 1500
                doOnEnd { splashScreenView.remove() }
            }

            val icon = splashScreenView.iconView
            val iconAnimator = ValueAnimator
                .ofInt(icon.height, 0)
                .setDuration(2000)

            iconAnimator.addUpdateListener {
                val value = it.animatedValue as Int
                icon.layoutParams.width = value
                icon.layoutParams.height = value
                icon.requestLayout()
                if (value == 0) slideBack.start()
            }

            AnimatorSet().apply {
                interpolator = AnticipateInterpolator(5f)
                play(iconAnimator)
                start()
            }

        }

    }

}
