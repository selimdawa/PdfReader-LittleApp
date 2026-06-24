package com.littleapp.pdfreader.Activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.littleapp.pdfreader.Unit.CLASS
import com.littleapp.pdfreader.Unit.THEME
import com.littleapp.pdfreader.Unit.VOID
import com.littleapp.pdfreader.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val timePerSecond = 2
    private val timeFinal = TIME_PER_MILLIS * timePerSecond

    override fun onCreate(savedInstanceState: Bundle?) {
        THEME.setThemeOfApp(this)
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({ launch() }, timeFinal.toLong())
    }

    private fun launch() {
        VOID.Intent1(this, CLASS.MAIN)
        finish()
    }

    companion object {
        private const val TIME_PER_MILLIS = 1000
    }
}