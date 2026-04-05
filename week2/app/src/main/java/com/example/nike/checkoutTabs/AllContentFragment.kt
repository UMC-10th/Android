package com.example.nike.checkoutTabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.R
import com.example.nike.adapter.CheckoutAdapter
import com.example.nike.data.CheckoutData
import com.example.nike.databinding.FragmentAllContentBinding

class AllContentFragment : Fragment() {
    private var _binding: FragmentAllContentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val allProductList = mutableListOf<CheckoutData>()
        allProductList.add(CheckoutData(R.drawable.product_image, false, "Nike Everyday Plus Cushioned", "Training Ankle Socks (6 Pairs)", "5 Colors", "US$10", true))
        allProductList.add(CheckoutData(R.drawable.product_image, false, "Nike Elite Crew", "Basketball Socks", "7 Colors", "US$16", false))
        allProductList.add(CheckoutData(R.drawable.product_image, true, "Nike Air Force 1 '07", "Women's Shoes", "5 Colors", "US$115", false))
        allProductList.add(CheckoutData(R.drawable.product_image, true, "Jordan ENike Air Force 1 '07ssentials", "Men's Shoes", "2 Colors", "US$115", false))

        val productAdapter = CheckoutAdapter(allProductList,
            onVisitClicked = { checkoutItem ->
                Toast.makeText(context, "${checkoutItem.name}을 구매할까?", Toast.LENGTH_SHORT).show()
            })

        binding.checkoutAllProducts.adapter = productAdapter
        binding.checkoutAllProducts.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}