package com.example.NikeApp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.NikeApp.R
import com.example.NikeApp.databinding.ActivityMainBinding
import com.example.NikeApp.ui.cart.CartFragment
import com.example.NikeApp.ui.home.HomeFragment
import com.example.NikeApp.ui.profile.ProfileFragment
import com.example.NikeApp.ui.purchase.PurchaseFragment
import com.example.NikeApp.ui.wishlist.WishlistFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 앱 처음 실행 시 HomeFragment 표시
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        // 탭 누를 때마다 Fragment 교체
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment())
                        .commit()
                    true
                }
                R.id.nav_purchase -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, PurchaseFragment())
                        .commit()
                    true
                }
                R.id.nav_wishlist -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, WishlistFragment())
                        .commit()
                    true
                }
                R.id.nav_cart -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, CartFragment())
                        .commit()
                    true
                }
                R.id.nav_profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}
