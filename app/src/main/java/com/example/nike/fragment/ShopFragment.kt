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
import com.example.nike.adapter.ProductAdapter
import com.example.nike.adapter.ScreenType // 명찰 임포트 잊지 마!
import com.example.nike.databinding.FragmentShopBinding

class ShopFragment : Fragment() {
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shopList = mutableListOf(
            ProductData("Nike Air Force 1 '07", "US$115", R.drawable.air_jordan, "BestSeller"),
            ProductData("Nike Everyday Plus", "US$10", R.drawable.air_jordan, ""),
            ProductData("Jordan ENike Air Force", "US$115", R.drawable.air_jordan, "BestSeller"),
            ProductData("Nike Elite Crew", "US$16", R.drawable.air_jordan, ""),
            ProductData("Nike Everyday Plus", "US$10", R.drawable.air_jordan, "", "Training Ankle Socks (6 Pairs)", "5 Colours", true),
            ProductData("Nike Dunk Low", "US$110", R.drawable.air_jordan, ""),
            ProductData("Nike Air Max", "US$130", R.drawable.air_jordan, "BestSeller"),
            ProductData("Nike Everyday Plus", "US$10", R.drawable.air_jordan, "", "Training Ankle Socks (6 Pairs)", "5 Colours", true),

        )

        // 💡 핵심 에러 해결: 명찰(ScreenType)을 달아주고, 람다 이름 명시!
        val shopAdapter = ProductAdapter(
            productList = shopList,
            screenType = ScreenType.SHOP, // "나 샵 화면이야!"
            onHeartClicked = { product ->
                // 나중에 위시리스트 저장 로직 들어갈 곳
            },
            onItemClicked = { product ->
                Toast.makeText(requireContext(), "${product.name} 선택!", Toast.LENGTH_SHORT).show()
            }
        )

        binding.shopRecyclerview.apply {
            adapter = shopAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}