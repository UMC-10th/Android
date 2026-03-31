package com.clone.nike.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.clone.nike.R
import com.clone.nike.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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


        //BottomNav (FragmentManager 기반)
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.main_fragment_container, HomeFragment())
//            .commit()
//
//        binding.mainBottomNav.setOnItemSelectedListener { item ->
//            when(item.itemId) {
//                R.id.homeFragment -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.main_fragment_container, HomeFragment())
//                        .commit()
//                    true
//                }
//
//                R.id.purchaseFragment -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.main_fragment_container, PurchaseFragment())
//                        .commit()
//                    true
//                }
//
//                R.id.cartFragment -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.main_fragment_container, CartFragment())
//                        .commit()
//                    true
//                }
//                R.id.wishFragment -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.main_fragment_container, WishFragment())
//                        .commit()
//                    true
//                }
//                R.id.profileFragment -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.main_fragment_container, ProfileFragment())
//                        .commit()
//                    true
//                }
//                else -> false
//            }
//        }
    }
}