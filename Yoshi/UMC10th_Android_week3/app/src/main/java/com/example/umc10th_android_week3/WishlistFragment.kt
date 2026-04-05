package com.example.umc10th_android_week3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umc10th_android_week3.databinding.FragmentWishlistragmentBinding

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 더미 데이터 생성
        val productList = mutableListOf(
            ProductData(R.drawable.ic_launcher_background, "DUMMY", "DUMMY", "DUMMY", "DUMMY"),
            ProductData(R.drawable.ic_launcher_background, "DUMMY", "DUMMY", "DUMMY", "DUMMY"),
            ProductData(R.drawable.ic_launcher_background, "DUMMY", "DUMMY", "DUMMY", "DUMMY"),
            ProductData(R.drawable.ic_launcher_background, "DUMMY", "DUMMY", "DUMMY", "DUMMY"),
        )

        // Adapter 생성 및 연결
        val adapter = ProductAdapter(productList)
        binding.rvWishlistProduct.adapter = adapter

        // GridLayoutManager 2열
        binding.rvWishlistProduct.layoutManager = GridLayoutManager(
            requireContext(),
            2
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}