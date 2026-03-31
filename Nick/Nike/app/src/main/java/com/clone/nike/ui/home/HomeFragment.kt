package com.clone.nike.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.clone.nike.R
import com.clone.nike.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = requireActivity().intent.getStringExtra("title")
        binding.homeTitleTV.text = title

        //뒤로가기 버튼 인식 콜백
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)

        //더미 데이터
        val newGoodsDataList = mutableListOf(
            NewGoodsData(R.drawable.image_jordan_xxxvi,"Air jordan XXXVI","US$185"),
            NewGoodsData(R.drawable.image_air_force_1, "Nike Air Force 1 '07", "US$115")
        )

        val adapter = NewRVAdapter(newGoodsDataList)
        binding.homeNewRV.adapter = adapter
        binding.homeNewRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    // 뒤로가기 두번 클릭 시 종료
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        var pressedTime:Long = 0;
        override fun handleOnBackPressed() {
            if(System.currentTimeMillis() - pressedTime >= 2000) {
                pressedTime = System.currentTimeMillis()
                Toast.makeText(requireContext(), "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
            }
            else {
                requireActivity().finishAffinity()
            }
        }

    }
}