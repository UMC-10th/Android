package com.example.nike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nike.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

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

        // 1. 우리가 만든 창고 관리자 부르기
        val shoeDataStore = ShoeDataStore(requireContext())

        // 2. 홈 화면용 더미 데이터
        val homeDummyList = arrayListOf(
            ShoeData(R.drawable.img_shoe_1, "Air Jordan XXXVI", "New Release", "1 Colour", "US$185", false),
            ShoeData(R.drawable.img_shoe_2, "Nike Air Force 1", "Best Seller", "3 Colours", "US$115", false)
        )

        // 3. 구매하기 화면용 더미 데이터 (최초 1회 저장을 위해 여기서 같이 정의)
        val shopDummyList = arrayListOf(
            ShoeData(R.drawable.img_shoe_1, "Nike Everyday Plus Cushioned", "Training Ankle Socks", "5 Colours", "US$10", false),
            ShoeData(R.drawable.img_shoe_2, "Nike Elite Crew", "Basketball Socks", "7 Colours", "US$16", false),
            ShoeData(R.drawable.img_shoe_1, "Nike Air Force 1 '07", "Women's Shoes", "5 Colours", "US$115", false),
            ShoeData(R.drawable.img_shoe_2, "Air Jordan 1 Mid", "Men's Shoes", "2 Colours", "US$125", false)
        )

        // 4. 코루틴 출동! (창고 검사 및 데이터 세팅)
        viewLifecycleOwner.lifecycleScope.launch {
            shoeDataStore.getHomeShoes().collect { savedHomeShoes ->
                if (savedHomeShoes.isEmpty()) {
                    // 창고가 비었다면 (앱 최초 실행) -> 준비해둔 더미데이터들을 창고에 몽땅 저장!
                    shoeDataStore.saveHomeShoes(homeDummyList)
                    shoeDataStore.saveShopShoes(shopDummyList)
                } else {
                    // 창고에 데이터가 있다면 -> 꺼내서 화면에 띄우기!
                    val shoeAdapter = HomeShoeAdapter(savedHomeShoes)
                    binding.rvHome.adapter = shoeAdapter
                    binding.rvHome.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}