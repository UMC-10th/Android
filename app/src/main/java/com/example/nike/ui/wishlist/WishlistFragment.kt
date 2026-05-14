package com.example.nike.ui.wishlist

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
import com.example.nike.databinding.FragmentWishlistBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WishlistFragment : Fragment() {
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WishlistViewModel by viewModels()
    private lateinit var wishlistAdapter: ProductAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wishlistAdapter = ProductAdapter(
            productList = mutableListOf(),
            screenType = ScreenType.WISHLIST,
            onHeartClicked = { product -> viewModel.removeFromWishlist(product) },
            onItemClicked = { product ->
                Toast.makeText(requireContext(), "${product.name} 확인!", Toast.LENGTH_SHORT).show()
            }
        )

        binding.wishlistRecyclerview.apply {
            adapter = wishlistAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(false)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.wishlist.collect { likedProducts ->
                wishlistAdapter.updateData(likedProducts)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}