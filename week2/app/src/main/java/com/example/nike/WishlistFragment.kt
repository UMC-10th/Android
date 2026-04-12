package com.example.nike

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.adapter.ProductAdapter
import com.example.nike.data.PreferenceManager
import com.example.nike.data.ProductData
import com.example.nike.databinding.FragmentWishlistBinding
import kotlinx.coroutines.launch

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceManager = PreferenceManager(requireContext())

        // data store 저장
        viewLifecycleOwner.lifecycleScope.launch {
            preferenceManager.wishlistDataListFlow.collect { savedList ->
                if (savedList.isEmpty()) {
                    val dummyData = createWishlistDummy()
                    preferenceManager.saveWishlistDataList(dummyData)
                } else {
                    setupRecyclerView(savedList)
                }
            }
        }
    }

    private fun setupRecyclerView(list: List<ProductData>) {
        val adapter = ProductAdapter(
            list,
            onVisitClicked = { wishlistItem ->
                Toast.makeText(context, "${wishlistItem.name} 사고싶어요", Toast.LENGTH_SHORT).show()
            })

        binding.wishlistRecyclerView.adapter = adapter
        binding.wishlistRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun createWishlistDummy(): List<ProductData> {
        return mutableListOf<ProductData>().apply{
            add(ProductData(R.drawable.product_image, false, "Air Jordan 1 Mid", null, null, "US$125", null))
            add(ProductData(R.drawable.product_image, false, "Nike Everyday Plus Cushioned", "Training Ankle Socks (6 Pairs)", "5 Colors", "US$10", null))
            add(ProductData(R.drawable.product_image, false, "Nike Air Max 270", null, "3 Colors", "US$160", null))
            add(ProductData(R.drawable.product_image, false, "Nike Sportswear Tech Fleece", "Men's Full-Zip Hoodie", null, "US$130", null))
            add(ProductData(R.drawable.product_image, false, "Nike Dunk Low", "Women's Shoes", "1 Color", "US$115", null))
            add(ProductData(R.drawable.product_image, false, "Nike Brasilia 9.5", null, null, "US$37", null))
            add(ProductData(R.drawable.product_image, false, "Air Jordan 1 Low", "Men's Shoes", "4 Colors", "US$110", null))
            add(ProductData(R.drawable.product_image, false, "Nike Dri-FIT", "Men's Training T-Shirt", "2 Colors", "US$30", null))
            add(ProductData(R.drawable.product_image, false, "Nike Blazer Mid '77", null, "5 Colors", "US$105", null))
            add(ProductData(R.drawable.product_image, false, "Nike Pegasus 40", "Road Running Shoes", null, "US$130", null))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}