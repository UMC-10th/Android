package com.example.nike

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.nike.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 이름표
    private val TAG = "LIFE_QUIZ"

    // 뷰바인딩 단축 번호판 준비
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // 설계도를 바인딩으로 연결해서 화면에 띄우기
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // 생명주기 로그 확인: 태어났다고 기록 남김
        Log.d(TAG, "onCreate")

        // =========================================================================
        // 🌟 STEP 4: 내비게이션(NavGraph)과 하단 탭 연결하기
        // (기존의 changeFragment, setOnItemSelectedListener는 이제 필요 없어서 지웠습니다!)
        // =========================================================================

        // 1. 특수 액자(NavHostFragment)에서 내비게이션 조종기(navController)를 찾아옵니다.
        // (주의: xml 파일의 FragmentContainerView 아이디가 'main_frm'이라고 가정했습니다! ㅇㅇ 맞음.)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_frm) as NavHostFragment
        val navController = navHostFragment.navController

        // 2. 하단 탭(BottomNav)과 조종기를 선으로 연결합니다! (운명 공동체 결성)
        binding.bottomNav.setupWithNavController(navController)

    }

    // =========================================================================
    // 아래는 채령님이 작성하신 생명주기 로그 기록들입니다 (수정 안 함!)
    // =========================================================================
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