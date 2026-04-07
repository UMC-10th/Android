package com.example.nike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager // 🌟 Grid 필수 Import
import com.example.nike.databinding.FragmentWishlistBinding

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 🌟 화면 생성 후 데이터 넣기
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. 위시리스트용 더미 데이터
        val wishlistDummyList = arrayListOf(
            ShoeData(R.drawable.img_shoe_2, "Air Jordan 1 Mid", "Basketball Shoes", "1 Colour", "US$125"),
            ShoeData(R.drawable.img_shoe_1, "Nike Everyday Plus Cushioned", "Training Ankle Socks", "5 Colours", "US$10")
        )

        val shoeAdapter = ShoeAdapter(wishlistDummyList)

        // 2. 벨트 장착 및 2칸씩(Grid) 세팅
        binding.rvWishlist.adapter = shoeAdapter
        binding.rvWishlist.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}