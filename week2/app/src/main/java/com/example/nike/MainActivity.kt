package com.example.nike

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.nike.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // navController 연결
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        binding.mainBnv.setupWithNavController(navController)

        // 뒤로가기 두 번 누르면 나가기
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() - backPressedTime < 2000) {
                    finish()
                } else {
                    Toast.makeText(this@MainActivity, "뒤로가기 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
                    backPressedTime = System.currentTimeMillis()
                }
            }
        }

        this.onBackPressedDispatcher.addCallback(this, callback)

    }
}