package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val stamp1 = findViewById<ImageView>(R.id.iv_stamp1)
        val text1 = findViewById<TextView>(R.id.tv_stamp1_desc)

        stamp1.setOnClickListener {
            text1.setTextColor(Color.parseColor("#3F51B5"))
        }
    }
}