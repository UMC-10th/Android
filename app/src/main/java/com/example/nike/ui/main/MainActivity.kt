package com.example.nike.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.nike.R
import com.example.nike.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint  // 이 줄 추가
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. NavHostFragment 찾기 (반드시 as NavHostFragment 필요)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_frm) as NavHostFragment

        // 4. navController 가져오기
        val navController = navHostFragment.navController

        // 5. binding. 을 붙여서 main_bnv 호출하기!
        binding.mainBnv.setupWithNavController(navController) }


}