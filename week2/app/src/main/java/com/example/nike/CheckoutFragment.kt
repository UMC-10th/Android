package com.example.nike

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nike.adapter.CheckoutAdapter
import com.example.nike.adapter.CheckoutPagerAdapter
import com.example.nike.databinding.FragmentCheckoutBinding

class CheckoutFragment : Fragment() {

    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = CheckoutPagerAdapter(this)
        binding.checkoutViewPager.adapter = pagerAdapter

        com.google.android.material.tabs.TabLayoutMediator(
            binding.checkoutTabLayout,
            binding.checkoutViewPager
        ) { tab, position ->
            tab.text = when (position) {
                0 -> "전체"
                1 -> "Tops&T-Shirts"
                else -> "Shoes"
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}