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

        // 내 화면의 '주문하기' 버튼 누르기
        // xml에 있던 btn_order가 카멜 표기법(btnOrder)으로 알아서 바뀝니다
        binding.btnOrder.setOnClickListener {

            // 부모 스케치북(MainActivity)의 단축 번호판을 빌려오기
            val mainActivity = requireActivity() as MainActivity

            // 부모 번호판(binding)에 있는 하단 탭(bottomNav)을 조종합니다
            mainActivity.binding.bottomNav.selectedItemId = R.id.tab_shop
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 메모리 정리를 위해 포스트잇 뗄 때 번호판 비우기
        _binding = null
    }
}