package com.example.umc10th_android_week3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umc10th_android_week3.databinding.FragmentPurchaseBinding

class PurchaseFragment : Fragment() {

    private var _binding: FragmentPurchaseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPurchaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 탭 추가
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("전체"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tops & T-Shirts"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Shoes"))

        // 더미 데이터
        val productList = mutableListOf(
            ProductData(R.drawable.ic_launcher_background, "DUMMY", "DUMMY", "DUMMY", "DUMMY"),
            ProductData(R.drawable.ic_launcher_background, "DUMMY", "DUMMY", "DUMMY", "DUMMY"),
            ProductData(R.drawable.ic_launcher_background, "DUMMY", "DUMMY", "DUMMY", "DUMMY"),
            ProductData(R.drawable.ic_launcher_background, "DUMMY", "DUMMY", "DUMMY", "DUMMY"),
            ProductData(R.drawable.ic_launcher_background, "DUMMY", "DUMMY", "DUMMY", "DUMMY"),
            ProductData(R.drawable.ic_launcher_background, "DUMMY", "DUMMY", "DUMMY", "DUMMY"),
        )

        // Adapter 생성 및 연결
        val adapter = ProductAdapter(productList)
        binding.rvPurchaseProduct.adapter = adapter

        // GridLayoutManager로 2열 격자 배치
        binding.rvPurchaseProduct.layoutManager = GridLayoutManager(
            requireContext(),
            2
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}