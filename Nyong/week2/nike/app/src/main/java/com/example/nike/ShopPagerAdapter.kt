package com.example.nike

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ShopPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    // 총 탭 개수
    override fun getItemCount(): Int = 3

    // position에 따라 보여줄 Fragment 결정
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ShopAllFragment()
            1 -> ShopTopFragment()
            2 -> ShopSaleFragment()
            else -> ShopAllFragment()
        }
    }
}