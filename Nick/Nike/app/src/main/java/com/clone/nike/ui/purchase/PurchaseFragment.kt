package com.clone.nike.ui.purchase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clone.nike.databinding.FragmentPurchaseBinding
import com.clone.nike.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class PurchaseFragment: BaseFragment<FragmentPurchaseBinding>(FragmentPurchaseBinding::inflate) {
    override fun initView() {
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