package com.example.umc10th_android_week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class CartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        val btnOrder = view.findViewById<Button>(R.id.btn_order)

        btnOrder.setOnClickListener {
            // 구매하기 Fragment로 교체
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PurchaseFragment())
                .commit()

            // 하단 탭도 구매하기로 선택 변경
            val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav)
            bottomNav.selectedItemId = R.id.nav_purchase
        }

        return view
    }
}