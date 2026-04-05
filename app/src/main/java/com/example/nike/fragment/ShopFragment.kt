package com.example.nike.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.ProductData
import com.example.nike.R
import com.example.nike.adapter.ShopAdapter
import com.example.nike.databinding.FragmentShopBinding

class ShopFragment : Fragment() {
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 뷰 바인딩 연결
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. 더미 데이터 리스트 생성
        // 중요: ProductData(name, price, coverImg, tag) 순서를 반드시 지킬 것!
        val shopList = arrayListOf(
            ProductData("Nike Air Force 1 '07", "US$115", R.drawable.air_jordan, "BestSeller"),
            ProductData("Nike Everyday Plus", "US$10", R.drawable.air_jordan, ""),
            ProductData("Jordan ENike Air Force", "US$115", R.drawable.air_jordan, "BestSeller"),
            ProductData("Nike Elite Crew", "US$16", R.drawable.air_jordan, ""),
            ProductData("Nike Dunk Low", "US$110", R.drawable.air_jordan, ""),
            ProductData("Nike Air Max", "US$130", R.drawable.air_jordan, "BestSeller")
        )

        // 2. 어댑터 초기화 (클릭 리스너 포함)
        val shopAdapter = ShopAdapter(shopList) { product ->
            // 아이템 클릭 시 토스트 메시지 출력
            Toast.makeText(requireContext(), "${product.name} 선택!", Toast.LENGTH_SHORT).show()
        }

        // 3. 리사이클러뷰 설정
        binding.shopRecyclerview.apply {
            adapter = shopAdapter
            // 2단 그리드 레이아웃 설정 (context 대신 requireContext() 권장)
            layoutManager = GridLayoutManager(requireContext(), 2)

            // 성능 최적화를 위해 아이템 크기가 고정임을 알림
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 메모리 누수 방지를 위해 바인딩 해제
        _binding = null
    }
}