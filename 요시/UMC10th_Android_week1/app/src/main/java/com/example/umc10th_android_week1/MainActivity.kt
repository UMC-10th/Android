package com.example.umc10th_android_week1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var selectedTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupEmotion(
            imageId = R.id.imgHappy,
            textId = R.id.txtHappy,
            color = Color.parseColor("#FFB300")  // 노란색 (행복)
        )
        setupEmotion(
            imageId = R.id.imgExcited,
            textId = R.id.txtExcited,
            color = Color.parseColor("#B8F8FB")  // 초록색 (흥분)
        )
        setupEmotion(
            imageId = R.id.imgNormal,
            textId = R.id.txtNormal,
            color = Color.parseColor("#9C27B0")  // 파란색 (평범)
        )
        setupEmotion(
            imageId = R.id.imgAnxious,
            textId = R.id.txtAnxious,
            color = Color.parseColor("#4CAF50")  // 보라색 (불안)
        )
        setupEmotion(
            imageId = R.id.imgAngry,
            textId = R.id.txtAngry,
            color = Color.parseColor("#F44336")  // 빨간색 (화남)
        )
    }
    private fun setupEmotion(imageId: Int, textId: Int, color: Int) {
        val imageView = findViewById<ImageView>(imageId)
        val textView = findViewById<TextView>(textId)

        imageView.setOnClickListener {
            // 이전에 선택된 텍스트 원래 색으로 복구
            selectedTextView?.setTextColor(Color.BLACK)

            // 현재 클릭된 텍스트 색상 변경
            textView.setTextColor(color)

            // 현재 선택 저장
            selectedTextView = textView
        }
    }
}