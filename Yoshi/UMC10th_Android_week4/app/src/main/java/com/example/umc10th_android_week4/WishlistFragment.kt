package com.example.umc10th_android_week4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umc10th_android_week4.databinding.FragmentWishlistragmentBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var productStorage: ProductStorage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productStorage = ProductStorage(requireContext())

        lifecycleScope.launch {
            val allProducts = productStorage.getProducts().first()

            // 하트가 눌린 상품만 필터링
            val likedProducts = allProducts.filter { it.isLiked }.toMutableList()

            val adapter = ProductAdapter(likedProducts) { position, product ->
                lifecycleScope.launch {
                    // 전체 목록에서 해당 상품 찾아서 업데이트
                    val fullList = productStorage.getProducts().first().toMutableList()
                    val index = fullList.indexOfFirst { it.productName == product.productName }
                    if (index != -1) {
                        fullList[index] = product
                        productStorage.saveProducts(fullList)
                    }

                    // 하트 해제 시 위시리스트에서 제거
                    if (!product.isLiked) {
                        likedProducts.removeAt(position)
                        binding.rvWishlistProduct.adapter?.notifyItemRemoved(position)
                    }
                }
            }

            binding.rvWishlistProduct.adapter = adapter
            binding.rvWishlistProduct.layoutManager = GridLayoutManager(
                requireContext(), 2
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}