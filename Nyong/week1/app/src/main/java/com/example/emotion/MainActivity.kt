package com.example.emotion

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. XML에 있는 5개의 '우표 이미지'를 코드로 찾아옵니다.
        val imgHappy = findViewById<ImageView>(R.id.img_happy)
        val imgExcited = findViewById<ImageView>(R.id.img_excited)
        val imgNormal = findViewById<ImageView>(R.id.img_normal)
        val imgSad = findViewById<ImageView>(R.id.img_sad)
        val imgAngry = findViewById<ImageView>(R.id.img_angry)

        // 2. XML에 있는 5개의 '설명 텍스트'를 코드로 찾아옵니다.
        val tvHappy = findViewById<TextView>(R.id.tv_happy)
        val tvExcited = findViewById<TextView>(R.id.tv_excited)
        val tvNormal = findViewById<TextView>(R.id.tv_normal)
        val tvSad = findViewById<TextView>(R.id.tv_sad)
        val tvAngry = findViewById<TextView>(R.id.tv_angry)

        // 3. 각각의 우표를 클릭했을 때 글자 색깔이 변하도록 이벤트를 달아줍니다.

        // ① 행복 우표 (노란색)
        imgHappy.setOnClickListener {
            tvHappy.setTextColor(Color.parseColor("#FFB300"))
        }

        // ② 흥분 우표 (초록색)
        imgExcited.setOnClickListener {
            tvExcited.setTextColor(Color.parseColor("#4CAF50"))
        }

        // ③ 평범 우표 (보라색)
        imgNormal.setOnClickListener {
            tvNormal.setTextColor(Color.parseColor("#9C27B0"))
        }

        // ④ 슬픔 우표 (파란색)
        imgSad.setOnClickListener {
            tvSad.setTextColor(Color.parseColor("#2196F3"))
        }

        // ⑤ 화남 우표 (빨간색)
        imgAngry.setOnClickListener {
            tvAngry.setTextColor(Color.parseColor("#F44336"))
        }
    }
}