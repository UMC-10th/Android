package com.example.androidstudy

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.androidstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectedTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val items = listOf(
                Triple(mainFaceAngryTv,mainFaceAngryIv,Color.RED),
                Triple(mainFaceSosoTv,mainFaceSosoIv,Color.CYAN),
                Triple(mainFaceHappyTv,mainFaceHappyIv,Color.YELLOW),
                Triple(mainFaceExcitedTv,mainFaceExcitedIv,Color.BLUE),
                Triple(mainFaceNervousTv,mainFaceNervousIv,Color.GREEN)
            )

            items.forEach { (textView, imageView, color) ->
                imageView.setOnClickListener {
                    selectedTextView?.setTextColor(Color.BLACK)
                    textView.setTextColor(color)
                    selectedTextView = textView
                }
            }
        }
    }
}