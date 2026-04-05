package com.example.nike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
// 바인딩 클래스를 불러옵니다! (패키지명.databinding.도면이름Binding)
import com.example.nike.databinding.FragmentShopBinding

class ShopFragment : Fragment() {

    // 1. 메모리 누수를 막기 위한 '임시' 번호판 (_binding)
    private var _binding: FragmentShopBinding? = null

    // 2. 실제로 편하게 쓸 '진짜' 번호판 (binding)
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 3. 도면을 부풀려서 번호판에 채워 넣기! (기존 R.layout 방식 대신 바인딩 사용)
        _binding = FragmentShopBinding.inflate(inflater, container, false)

        // 4. 완성된 포스트잇 덩어리(root)를 넘겨주기
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 5. 포스트잇이 떼어질 때 번호판도 깔끔하게 비워주기 (메모리 관리!)
        _binding = null
    }
}