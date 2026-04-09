package com.example.nike.adapter // 패키지 경로 확인

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nike.checkoutTabs.AllContentFragment

class CheckoutPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllContentFragment()
            1 -> AllContentFragment() // 임시로 같은 탭 사용
            else -> AllContentFragment() // 임시로 같은 탭 사용
        }
    }
}