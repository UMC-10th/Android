package com.example.nike.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.adapter.ProductAdapter
import com.example.nike.adapter.ScreenType
import com.example.nike.databinding.FragmentShopBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShopFragment : Fragment() {
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShopViewModel by viewModels()
    private lateinit var shopAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shopAdapter = ProductAdapter(
            productList = mutableListOf(),
            screenType = ScreenType.SHOP,
            onHeartClicked = { product -> viewModel.toggleHeart(product) },
            onItemClicked = { product ->
                Toast.makeText(requireContext(), "${product.name} 선택!", Toast.LENGTH_SHORT).show()
            }
        )

        binding.shopRecyclerview.apply {
            adapter = shopAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(false)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.products.collect { products ->
                shopAdapter.updateData(products)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}