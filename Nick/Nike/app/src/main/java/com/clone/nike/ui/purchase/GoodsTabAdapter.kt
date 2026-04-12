package com.clone.nike.ui.purchase

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.clone.nike.ui.purchase.tab.AllFragment
import com.clone.nike.ui.purchase.tab.SaleFragment
import com.clone.nike.ui.purchase.tab.TopFragment

class GoodsTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllFragment()
            1 -> TopFragment()
            else -> SaleFragment()
        }
    }
}