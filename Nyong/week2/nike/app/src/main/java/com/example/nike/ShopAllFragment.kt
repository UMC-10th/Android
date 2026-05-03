package com.example.nike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.databinding.FragmentShopAllBinding
import kotlinx.coroutines.launch

class ShopAllFragment : Fragment() {

    private var _binding: FragmentShopAllBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 화면이 완전히 만들어진 뒤 실행
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // DataStore 객체 생성
        val shoeDataStore = ShoeDataStore(requireContext())

        // DataStore에서 구매하기 상품 목록을 가져옴
        viewLifecycleOwner.lifecycleScope.launch {
            shoeDataStore.getShopShoes().collect { savedShopShoes ->

                // RecyclerView Adapter 생성
                val shoeAdapter = ShoeAdapter(savedShopShoes) { clickedPosition ->

                    // 클릭한 상품의 하트 상태 반전
                    savedShopShoes[clickedPosition].isLiked =
                        !savedShopShoes[clickedPosition].isLiked

                    // 변경된 상품 목록을 다시 DataStore에 저장
                    viewLifecycleOwner.lifecycleScope.launch {
                        shoeDataStore.saveShopShoes(savedShopShoes)

                        // 좋아요가 true인 상품만 위시리스트에 저장
                        val wishlist = savedShopShoes.filter { it.isLiked } as ArrayList<ShoeData>
                        shoeDataStore.saveWishlist(wishlist)
                    }
                }

                // RecyclerView에 Adapter와 GridLayoutManager 연결
                binding.rvShopAll.adapter = shoeAdapter
                binding.rvShopAll.layoutManager = GridLayoutManager(requireContext(), 2)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}