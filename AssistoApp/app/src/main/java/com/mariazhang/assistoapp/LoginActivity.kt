package com.mariazhang.assistoapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageButton
import android.widget.ScrollView
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.auth.FirebaseAuth
import com.mariazhang.assistoapp.databinding.ActivityLoginBinding
import com.mariazhang.assistoapp.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authenti: FirebaseAuth

    override fun onBackPressed() {}

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(ActivityLoginBinding.inflate(layoutInflater).also { binding = it }.root)

        val screenSplash = installSplashScreen()

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
            val intent = Intent(this, AyudaActivity::class.java)
            startActivity(intent)
        }


    }
}
