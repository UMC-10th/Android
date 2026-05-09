package com.clone.nike.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.clone.nike.R
import com.clone.nike.databinding.ActivityMainBinding
import com.clone.nike.ui.purchase.GoodsData
import com.clone.nike.repository.repository.DataStoreRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val NEW_GOODS_DATA = stringPreferencesKey("new_goods_data")
    val GOODS_DATA = stringPreferencesKey("goods_data")

    var newGoodsDataList = mutableListOf<GoodsData>()
    var goodsDataList = mutableListOf<GoodsData>()

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



        if (newGoodsDataList.isEmpty()) {
            newGoodsDataList = addDummyData()
        } else {
            lifecycleScope.launch {
                dataStoreRepository.saveGoodsInfo(
                    goodsData = newGoodsDataList,
                    dataStore = NEW_GOODS_DATA
                )
            }
        }

        if(goodsDataList.isEmpty()) {
            goodsDataList = addGoodsData()
        } else {
            lifecycleScope.launch {
                dataStoreRepository.saveGoodsInfo(
                    goodsData = goodsDataList,
                    dataStore = GOODS_DATA
                )
            }
        }
    }

    private fun addDummyData(): MutableList<GoodsData> {
        //더미 데이터
        return mutableListOf<GoodsData>().apply {
            add(GoodsData(R.drawable.image_jordan_xxxvi, "Air jordan XXXVI", "shoes", "1 colour", "US$185", false))
            add(GoodsData(R.drawable.image_air_force_1,"Nike Air Force 1 '07","shoes","3 colours","US$115",false))
        }
    }

    private fun addGoodsData(): MutableList<GoodsData> {
        //더미 데이터
        return mutableListOf<GoodsData>().apply {
            add(GoodsData(R.drawable.image_nike_everyday_plus_cushioned,"Nike Everyday Plus Cushioned","Traning Ankle Socks (6 Pairs)","5 Colours", "US$10",false))
            add(GoodsData(R.drawable.image_nike_everyday_plus_cushioned,"Nike Everyday Plus Cushioned","Traning Ankle Socks (6 Pairs)","5 Colours", "US$10",false))
            add(GoodsData(R.drawable.image_nike_everyday_plus_cushioned,"Nike Everyday Plus Cushioned","Traning Ankle Socks (6 Pairs)","5 Colours", "US$10",false))
            add(GoodsData(R.drawable.image_nike_everyday_plus_cushioned,"Nike Everyday Plus Cushioned","Traning Ankle Socks (6 Pairs)","5 Colours", "US$10",false))
        }
    }
}