package com.clone.nike.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clone.nike.R
import com.clone.nike.databinding.FragmentCartBinding
import com.clone.nike.ui.base.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class CartFragment: BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate) {

    override fun initView() {
        //버튼 클릭 시 이동
        binding.cartOrderBT.setOnClickListener {
            requireActivity()
                .findViewById<BottomNavigationView>(R.id.main_bottomNav)
                .selectedItemId = R.id.purchaseFragment
        }
    }
}