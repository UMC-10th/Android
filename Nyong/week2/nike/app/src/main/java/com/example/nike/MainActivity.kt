package com.example.nike

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.nike.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 이름표
    private val TAG = "LIFE_QUIZ"

    // 뷰바인딩 단축 번호판 준비
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // 설계도를 바인딩으로 연결해서 화면에 띄우기
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // 생명주기 로그 확인: 태어났다고 기록 남김
        Log.d(TAG, "onCreate")

        // 앱을 맨 처음 켰을 때 '홈' 화면을 기본으로 띄워줍니다.
        if (savedInstanceState == null) {
            binding.bottomNav.selectedItemId = R.id.tab_home
            changeFragment(HomeFragment())
        }

        // 하단 탭을 눌렀을 때 화면을 바꿔주는 이벤트 달기
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tab_home -> {
                    changeFragment(HomeFragment())
                    true
                }
                R.id.tab_shop -> {
                    changeFragment(ShopFragment())
                    true
                }
                R.id.tab_wishlist -> {
                    changeFragment(WishlistFragment())
                    true
                }
                R.id.tab_cart -> {
                    changeFragment(CartFragment())
                    true
                }
                R.id.tab_profile -> {
                    changeFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    // 포스트잇(프래그먼트)을 교체해 주는 전용 함수
    // : 하단 탭(bottomNav)의 아이콘을 누르면 -> changeFragment라는 택시를 불러서 -> main_frm이라는 빈 도화지 공간에 새로운 포스트잇 화면을 덮어 씌워라
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, fragment) // main_frm(빈 공간)에 새 포스트잇을 붙여라!
            .commit()
    }

    // 로그 찍기
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }
}