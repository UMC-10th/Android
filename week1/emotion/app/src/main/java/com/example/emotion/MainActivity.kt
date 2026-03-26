package com.example.emotion

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.emotion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.happyImg.setOnClickListener {
            binding.happyText.setTextColor(ContextCompat.getColor(this, R.color.happy))
        }

        binding.excitedImg.setOnClickListener {
            binding.excitedText.setTextColor(ContextCompat.getColor(this, R.color.excited))
        }

        binding.normalImg.setOnClickListener {
            binding.normalText.setTextColor(ContextCompat.getColor(this, R.color.normal))
        }

        binding.anxiousImg.setOnClickListener {
            binding.anxiousText.setTextColor(ContextCompat.getColor(this, R.color.anxious))
        }

        binding.angerImg.setOnClickListener {
            binding.angerText.setTextColor(ContextCompat.getColor(this, R.color.anger))
        }
    }
}