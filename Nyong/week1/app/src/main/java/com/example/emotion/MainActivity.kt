package com.example.emotion

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
// 바인딩을 쓰기 위해 가져오는 주소
import com.example.emotion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 'binding'이라는 이름의 단축 번호판을 미리 준비
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 화면 설계도(activity_main.xml)를 단축 번호판에 모두 연결
        binding = ActivityMainBinding.inflate(layoutInflater)
        // 기존의 setContentView(R.layout.activity_main) 대신 바인딩된 화면의 뿌리(root)를 띄워준다
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 이벤트 추가
        fun resetAllTextColors() {
            // 이제 앞에 'binding.' 만 붙이면 XML에 있는 위젯 이름을 바로 꺼내 쓸수있다
            binding.tvHappy.setTextColor(Color.BLACK)
            binding.tvExcited.setTextColor(Color.BLACK)
            binding.tvNormal.setTextColor(Color.BLACK)
            binding.tvSad.setTextColor(Color.BLACK)
            binding.tvAngry.setTextColor(Color.BLACK)
        }

        // ① 행복 우표
        binding.imgHappy.setOnClickListener {
            resetAllTextColors()
            binding.tvHappy.setTextColor(Color.parseColor("#FFB300"))
        }

        // ② 흥분 우표
        binding.imgExcited.setOnClickListener {
            resetAllTextColors()
            binding.tvExcited.setTextColor(Color.parseColor("#2196F3"))
        }

        // ③ 평범 우표
        binding.imgNormal.setOnClickListener {
            resetAllTextColors()
            binding.tvNormal.setTextColor(Color.parseColor("#9C27B0"))
        }

        // ④ 슬픔 우표
        binding.imgSad.setOnClickListener {
            resetAllTextColors()
            binding.tvSad.setTextColor(Color.parseColor("#4CAF50"))
        }

        // ⑤ 화남 우표
        binding.imgAngry.setOnClickListener {
            resetAllTextColors()
            binding.tvAngry.setTextColor(Color.parseColor("#F44336"))
        }
    }
}