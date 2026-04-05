package com.example.umc10th_android_week3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc10th_android_week3.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 더미 데이터
        val productList = mutableListOf(
            ProductData(R.drawable.ic_launcher_background, "DUMMY", "DUMMY", "DUMMY", "DUMMY"),
            ProductData(R.drawable.ic_launcher_background, "DUMMY", "DUMMY", "DUMMY", "DUMMY"),
            ProductData(R.drawable.ic_launcher_background, "DUMMY", "DUMMY", "DUMMY", "DUMMY"),
            ProductData(R.drawable.ic_launcher_background, "DUMMY", "DUMMY", "DUMMY", "DUMMY"),

        )

        // Adapter 생성
        val adapter = ProductAdapter(productList)

        // RecyclerView에 Adapter와 LayoutManager 연결
        binding.rvHomeProduct.adapter = adapter
        binding.rvHomeProduct.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}