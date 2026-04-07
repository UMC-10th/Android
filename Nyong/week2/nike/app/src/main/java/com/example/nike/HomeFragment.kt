package com.example.nike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager // 🌟 가로 스크롤을 위한 필수 Import
import com.example.nike.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 🌟 화면 생성 후 데이터 넣기
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. 홈 화면용 더미 데이터
        val homeDummyList = arrayListOf(
            ShoeData(R.drawable.img_shoe_1, "Air Jordan XXXVI", "New Release", "1 Colour", "US$185"),
            ShoeData(R.drawable.img_shoe_2, "Nike Air Force 1", "Best Seller", "3 Colours", "US$115")
        )

        val shoeAdapter = ShoeAdapter(homeDummyList)

        // 2. 벨트 장착 및 🌟 가로 방향(Horizontal)으로 세팅!
        binding.rvHome.adapter = shoeAdapter
        binding.rvHome.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}