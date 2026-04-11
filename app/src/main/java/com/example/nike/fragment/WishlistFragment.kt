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
import com.example.nike.adapter.ScreenType
import com.example.nike.databinding.FragmentWishlistBinding // 위시리스트 바인딩

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

        // 위시리스트니까 하트가 눌려있는(isLiked = true) 데이터라고 가정!
        val wishlist = mutableListOf(
            ProductData("Nike Dunk Low", "US$110", R.drawable.air_jordan, "","Training Ankle Socks (6 Pairs)", "5 Colours", isLiked = true),
            ProductData("Nike Air Max", "US$130", R.drawable.air_jordan, "", isLiked = true)
        )

        // 💡 샵이랑 다르게 명찰을 WISHLIST로 줌!
        val wishlistAdapter = ProductAdapter(
            productList = wishlist,
            screenType = ScreenType.WISHLIST,
            onHeartClicked = { product ->
                // 하트 해제 로직 들어갈 곳
            },
            onItemClicked = { product ->
                Toast.makeText(requireContext(), "${product.name} 확인!", Toast.LENGTH_SHORT).show()
            }
        )

        // 리사이클러뷰 아이디는 네 XML에 있는 아이디로 맞춰줘!
        binding.wishlistRecyclerview.apply {
            adapter = wishlistAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}