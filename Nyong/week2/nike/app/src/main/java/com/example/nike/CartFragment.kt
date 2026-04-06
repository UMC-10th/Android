package com.example.nike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
// 바인딩 클래스 불러오기
import com.example.nike.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    // 프래그먼트용 뷰바인딩 공식 세팅
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 도면을 부풀려서 번호판에 세팅
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOrder.setOnClickListener {
            // MainActivity에 있는 하단 탭의 '쇼핑' 버튼을 강제로 꾹 눌러줍니다!
            val mainActivity = activity as MainActivity
            mainActivity.binding.bottomNav.selectedItemId = R.id.tab_shop
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 메모리 정리를 위해 포스트잇 뗄 때 번호판 비우기
        _binding = null
    }
}