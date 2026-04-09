package com.example.nike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager // 🌟 Grid를 쓰기 위한 필수 Import
import com.example.nike.databinding.FragmentShopBinding

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 🌟 뷰(화면)가 완전히 만들어진 직후에 실행되는 함수 (여기서 데이터를 넣습니다!)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. 더미 데이터 명단 만들기 (이미지 이름은 채령님이 저장한 이름으로 수정하세요!)
        val shopDummyList = arrayListOf(
            ShoeData(R.drawable.img_shoe_1, "Nike Everyday Plus Cushioned", "Training Ankle Socks", "5 Colours", "US$10"),
            ShoeData(R.drawable.img_shoe_2, "Nike Elite Crew", "Basketball Socks", "7 Colours", "US$16"),
            ShoeData(R.drawable.img_shoe_1, "Nike Air Force 1 '07", "Women's Shoes", "5 Colours", "US$115"),
            ShoeData(R.drawable.img_shoe_2, "Air Jordan 1 Mid", "Men's Shoes", "2 Colours", "US$125")
        )

        // 2. 공장장(어댑터) 고용하기
        val shoeAdapter = ShoeAdapter(shopDummyList)

        // 3. 벨트에 장착하고 2칸씩(Grid) 쪼개라고 지시하기
        binding.rvShop.adapter = shoeAdapter
        binding.rvShop.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}