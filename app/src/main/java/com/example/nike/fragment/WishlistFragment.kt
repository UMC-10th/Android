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
import com.example.nike.adapter.WishlistAdapter
import com.example.nike.databinding.FragmentWishlistBinding

class WishlistFragment : Fragment() {
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 시안에 맞춘 더미 데이터 (설명이 없는 데이터와 있는 데이터 혼합)
        // 주의: ProductData(name, price, coverImg, tag, desc, colors) 순서 확인!
        val wishList = arrayListOf(
            ProductData("Air Jordan 1 Mid", "US$125", R.drawable.air_jordan, "", null, null),
            ProductData("Nike Everyday Plus Cushioned", "US$10", R.drawable.air_jordan, "", "Training Ankle Socks (6 Pairs)", "5 Colours")
        )

        // 어댑터 장착
        val wishAdapter = WishlistAdapter(wishList) { product ->
            Toast.makeText(requireContext(), "${product.name} 상세 보기", Toast.LENGTH_SHORT).show()
        }

        // 2단 그리드 매니저 장착
        binding.wishlistRecyclerview.apply {
            adapter = wishAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}