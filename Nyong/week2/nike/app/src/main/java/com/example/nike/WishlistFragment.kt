package com.example.nike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.databinding.FragmentWishlistBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. 창고 관리자 부르기
        val shoeDataStore = ShoeDataStore(requireContext())

        // 2. 코루틴 출동! (위시리스트 데이터 가져오기)
        viewLifecycleOwner.lifecycleScope.launch {

            // 창고에서 하트 눌린 데이터만 실시간으로 꺼내옵니다.
            shoeDataStore.getWishlist().collect { savedWishlist ->

                // 어댑터를 호출할 때 반드시 무전기(콜백 함수 { })를 달아줍니다!
                val shoeAdapter = ShoeAdapter(savedWishlist) { clickedPosition ->

                    // 위시리스트 화면에서 하트를 다시 눌러서 취소하려고 할 때의 동작
                    val unlikedShoe = savedWishlist[clickedPosition]

                    viewLifecycleOwner.lifecycleScope.launch {
                        // 1) 원본 창고(Shop)를 열어서, 지금 취소한 신발과 이름이 같은 녀석을 찾아 하트를 끕니다(false).
                        val shopList = shoeDataStore.getShopShoes().first()
                        val targetShoe = shopList.find { it.name == unlikedShoe.name }
                        if (targetShoe != null) {
                            targetShoe.isLiked = false
                        }

                        // 2) 원본 창고(Shop)에 하트 꺼진 상태로 덮어쓰기 완료!
                        shoeDataStore.saveShopShoes(shopList)

                        // 3) 위시리스트 창고도 하트 꺼진 애를 제외하고(true인 애들만 모아서) 덮어쓰기 완료!
                        val newWishlist = shopList.filter { it.isLiked } as ArrayList<ShoeData>
                        shoeDataStore.saveWishlist(newWishlist)
                    }
                }

                // 3. 벨트에 장착하고 2칸씩(Grid) 세팅
                binding.rvWishlist.adapter = shoeAdapter
                binding.rvWishlist.layoutManager = GridLayoutManager(requireContext(), 2)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}