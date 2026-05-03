package com.example.nike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.databinding.FragmentShopBinding
import kotlinx.coroutines.launch

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

    // 뷰(화면)가 완전히 만들어진 직후에 실행되는 함수
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. 창고 관리자 부르기
        val shoeDataStore = ShoeDataStore(requireContext())

        // 2. 코루틴 출동! (창고에서 데이터 꺼내오기)
        viewLifecycleOwner.lifecycleScope.launch {

            // 창고에서 구매하기 데이터(shop_shoes)를 실시간으로 가져옵니다
            shoeDataStore.getShopShoes().collect { savedShopShoes ->

                // 🌟 수정된 부분: 어댑터를 만들 때 하트 클릭 시 할 일을 중괄호 { } 안에 적어줍니다.
                val shoeAdapter = ShoeAdapter(savedShopShoes) { clickedPosition ->

                    // 1) 클릭된 신발의 하트 상태 반전 (true <-> false)
                    savedShopShoes[clickedPosition].isLiked = !savedShopShoes[clickedPosition].isLiked

                    // 2) 수정된 전체 리스트를 다시 창고(DataStore)에 덮어써서 영구 저장!
                    viewLifecycleOwner.lifecycleScope.launch {
                        shoeDataStore.saveShopShoes(savedShopShoes)

                        // 3) 위시리스트 업데이트: 하트가 눌린(isLiked == true) 신발들만 골라내서 따로 저장
                        val wishlist = savedShopShoes.filter { it.isLiked } as ArrayList<ShoeData>
                        shoeDataStore.saveWishlist(wishlist)
                    }
                }

                // 3. 벨트에 장착하고 2칸씩(Grid) 쪼개라고 지시하기
                binding.rvShop.adapter = shoeAdapter
                binding.rvShop.layoutManager = GridLayoutManager(requireContext(), 2)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}