package com.example.nike.checkoutTabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nike.R
import com.example.nike.adapter.HomeAdapter
import com.example.nike.adapter.ProductAdapter
import com.example.nike.data.HomeData
import com.example.nike.data.PreferenceManager
import com.example.nike.data.ProductData
import com.example.nike.databinding.FragmentAllContentBinding
import kotlinx.coroutines.launch

class AllContentFragment : Fragment() {
    private var _binding: FragmentAllContentBinding? = null
    private val binding get() = _binding!!
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceManager = PreferenceManager(requireContext())

        // data store 저장
        viewLifecycleOwner.lifecycleScope.launch {
            preferenceManager.checkoutDataListFlow.collect { savedList ->
                if (savedList.isEmpty()) {
                    val dummyData = createCheckoutDummy()
                    preferenceManager.saveCheckoutDataList(dummyData)
                } else {
                    setupRecyclerView(savedList)
                }
            }
        }
    }

    private fun setupRecyclerView(list: List<ProductData>) {
        val adapter = ProductAdapter(
            list,
            onVisitClicked = { checkoutItem ->
                Toast.makeText(context, "${checkoutItem.name} 구매할까요?", Toast.LENGTH_SHORT).show()
            })

        binding.checkoutAllProducts.adapter = adapter
        binding.checkoutAllProducts.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
    }

    private fun createCheckoutDummy(): List<ProductData> {
        return mutableListOf<ProductData>().apply{
            add(ProductData(R.drawable.product_image, false, "Nike Everyday Plus Cushioned", "Training Ankle Socks (6 Pairs)", "5 Colors", "US$10", true))
            add(ProductData(R.drawable.product_image, false, "Nike Elite Crew", "Basketball Socks", "7 Colors", "US$16", false))
            add(ProductData(R.drawable.product_image, true, "Nike Air Force 1 '07", "Women's Shoes", "5 Colors", "US$115", false))
            add(ProductData(R.drawable.product_image, true, "Jordan ENike Air Force 1 '07ssentials", "Men's Shoes", "2 Colors", "US$115", false))
            add(ProductData(R.drawable.product_image, true, "Nike Air Max 97", "Men's Shoes", "3 Colors", "US$175", true))
            add(ProductData(R.drawable.product_image, false, "Nike Sportswear Tech Fleece", "Men's Full-Zip Hoodie", "4 Colors", "US$130", false))
            add(ProductData(R.drawable.product_image, true, "Nike Dunk Low Retro", "Men's Shoes", "1 Color", "US$115", true))
            add(ProductData(R.drawable.product_image, false, "Nike Dri-FIT Adv", "Women's Running Tank", "2 Colors", "US$65", false))
            add(ProductData(R.drawable.product_image, true, "Air Jordan 1 Mid", "Men's Shoes", "8 Colors", "US$125", true))
            add(ProductData(R.drawable.product_image, false, "Nike Pro Warm", "Men's Long-Sleeve Top", "2 Colors", "US$55", false))
            add(ProductData(R.drawable.product_image, true, "Nike Blazer Mid '77", "Women's Shoes", "5 Colors", "US$105", false))
            add(ProductData(R.drawable.product_image, false, "Nike Heritage Waistpack", "Bags & Backpacks", "1 Color", "US$25", true))
            add(ProductData(R.drawable.product_image, true, "Nike Pegasus 40", "Men's Road Running Shoes", "6 Colors", "US$130", false))
            add(ProductData(R.drawable.product_image, false, "Nike Peak Beanie", "Hats & Headbands", "3 Colors", "US$28", false))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}