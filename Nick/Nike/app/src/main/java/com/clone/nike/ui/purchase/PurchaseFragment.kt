package com.clone.nike.ui.purchase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clone.nike.databinding.FragmentPurchaseBinding
import com.google.android.material.tabs.TabLayoutMediator

class PurchaseFragment: Fragment() {
    private lateinit var binding: FragmentPurchaseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPurchaseBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //tabLayout
        val adapter = GoodsTabAdapter(this)
        binding.purchaseTabLayoutVP.adapter = adapter

        TabLayoutMediator(binding.purchaseTabLayoutTL, binding.purchaseTabLayoutVP) { tab, position ->
            tab.text = when (position) {
                0 -> "전체"
                1 -> "Top&T-shirts"
                else -> "sale"
            }
        }.attach()
    }
}