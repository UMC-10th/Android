package com.clone.nike.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.clone.nike.R
import com.clone.nike.databinding.ActivityMainBinding
import com.clone.nike.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //BottomNav (Navigation 기반)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment_container)
                    as NavHostFragment

        val navController = navHostFragment.navController
        binding.mainBottomNav.setupWithNavController(navController)

        viewModel.inputData()
    }
}