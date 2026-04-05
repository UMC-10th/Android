package com.example.nike

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.adapter.ProductAdapter
import com.example.nike.data.ProductData
import com.example.nike.databinding.FragmentWishlistBinding

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wishlist = mutableListOf<ProductData>()
        wishlist.add(ProductData(R.drawable.product_image, false, "Air Jordan 1 Mid", null, null, "US$125", null))
        wishlist.add(ProductData(R.drawable.product_image, false, "Nike Everyday Plus Cushioned", "Training Ankle Socks (6 Pairs)", "5 Colors", "US$10", null))

        val productAdapter = ProductAdapter(wishlist,
            onVisitClicked = { wishlistItem ->
                Toast.makeText(context, "${wishlistItem.name}을 살까말까", Toast.LENGTH_SHORT).show()
            })

        binding.wishlistRecyclerView.adapter = productAdapter
        binding.wishlistRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}