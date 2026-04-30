package com.example.nike.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.DataManager // 💡 본인 프로젝트 경로에 맞게 임포트!
import com.example.nike.ProductData
import com.example.nike.adapter.ProductAdapter
import com.example.nike.adapter.ScreenType
import com.example.nike.databinding.FragmentWishlistBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class WishlistFragment : Fragment() {
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    // 💡 DataManager 선언
    private lateinit var dataManager: DataManager
    private lateinit var wishlistAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataManager = DataManager(requireContext())

        wishlistAdapter = ProductAdapter(
            productList = mutableListOf(),
            screenType = ScreenType.WISHLIST,
            onHeartClicked = { product ->
                // 위시리스트에서 하트 누르면 삭제되어야 함
                removeHeartStatus(product)
            },
            onItemClicked = { product ->
                Toast.makeText(requireContext(), "${product.name} 확인!", Toast.LENGTH_SHORT).show()
            }
        )

        binding.wishlistRecyclerview.apply {
            adapter = wishlistAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(false)
        }

        // 💡 DataStore 실시간 관찰 + 필터링!
        viewLifecycleOwner.lifecycleScope.launch {
            dataManager.getProducts().collect { allProducts ->
                // 전체 상품 중 하트가 눌린(isLiked == true) 상품만 골라내기
                val likedProducts = allProducts.filter { it.isLiked }

                // 골라낸 리스트만 어댑터에 전달
                wishlistAdapter.updateData(likedProducts)
            }
        }
    }

    // 💡 하트를 해제하고 DataStore 업데이트하는 함수
    private fun removeHeartStatus(product: ProductData) {
        viewLifecycleOwner.lifecycleScope.launch {
            val currentList = dataManager.getProducts().first().toMutableList()
            val index = currentList.indexOfFirst { it.name == product.name }

            if (index != -1) {
                // 위시리스트에서는 무조건 false로 변경 (하트 해제)
                currentList[index].isLiked = false
                dataManager.saveProducts(currentList)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}