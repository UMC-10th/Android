package com.example.nike.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nike.fragment.SaleFragment
import com.example.nike.fragment.TopTshirtsFragment

class ShopPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    // 탭의 개수 (Top & T-shirts, Sale 총 2개)
    override fun getItemCount(): Int = 2

    // 포지션에 따라 어떤 Fragment를 보여줄지 결정
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TopTshirtsFragment()
            else -> SaleFragment()
        }
    }
}