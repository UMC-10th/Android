package com.clone.nike

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.clone.nike.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뒤로가기 버튼 인식 콜백
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

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

    // 뒤로가기 두번 클릭 시 종료
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        var pressedTime:Long = 0;
        override fun handleOnBackPressed() {
            if(System.currentTimeMillis() - pressedTime >= 2000) {
                pressedTime = System.currentTimeMillis()
                Toast.makeText(this@MainActivity, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
            }
            else {
                finishAffinity()
            }
        }

    }
}