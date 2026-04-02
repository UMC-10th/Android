package com.clone.nike

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_splash)

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("title", "Discover")

        lifecycleScope.launch {
            delay(1000)
            startActivity(intent)
            finish()
        }
    }
}