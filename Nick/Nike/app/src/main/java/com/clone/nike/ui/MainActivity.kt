package com.clone.nike.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.clone.nike.R
import com.clone.nike.databinding.ActivityMainBinding
import com.clone.nike.ui.purchase.GoodsData
import com.clone.nike.ui.repository.DataStoreRepository
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val NEW_GOODS_DATA = stringPreferencesKey("new_goods_data")
    val GOODS_DATA = stringPreferencesKey("goods_data")

    private val dataStoreRepository by lazy {
        DataStoreRepository(this)
    }

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

        //더미 데이터
        val newGoodsDataList = mutableListOf(
            GoodsData(R.drawable.image_jordan_xxxvi,"Air jordan XXXVI","shoes","1 colour","US$185",false),
            GoodsData(R.drawable.image_air_force_1, "Nike Air Force 1 '07","shoes","3 colours", "US$115", false)
        )

        //더미 데이터
        val goodsDataList = mutableListOf(
            GoodsData(R.drawable.image_nike_everyday_plus_cushioned,"Nike Everyday Plus Cushioned","Traning Ankle Socks (6 Pairs)","5 Colours", "US$10",false),
            GoodsData(R.drawable.image_nike_everyday_plus_cushioned,"Nike Everyday Plus Cushioned","Traning Ankle Socks (6 Pairs)","5 Colours", "US$10",false),
            GoodsData(R.drawable.image_nike_everyday_plus_cushioned,"Nike Everyday Plus Cushioned","Traning Ankle Socks (6 Pairs)","5 Colours", "US$10",false),
            GoodsData(R.drawable.image_nike_everyday_plus_cushioned,"Nike Everyday Plus Cushioned","Traning Ankle Socks (6 Pairs)","5 Colours", "US$10",false)
        )

        lifecycleScope.launch {
            dataStoreRepository.saveGoodsInfo(
                goodsData = newGoodsDataList,
                dataStore = NEW_GOODS_DATA
            )
            dataStoreRepository.saveGoodsInfo(
                goodsData = goodsDataList,
                dataStore = GOODS_DATA
            )
        }



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