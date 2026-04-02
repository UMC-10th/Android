package com.example.nike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class CartFragment : Fragment() {

    // 포스트잇(프래그먼트)의 화면을 그려주는 핵심 마법 함수입니다!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // fragment_cart.xml 이라는 도면을 가져와서 화면에 부풀려라(inflate)!
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    // 화면(뷰)이 완전히 만들어진 직후에 실행되는 함수
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. 도화지(view)에서 '주문하기' 버튼을 찾아옵니다.
        val btnOrder = view.findViewById<Button>(R.id.btn_order)

        // 2. 버튼이 눌렸을 때 할 일을 정해줍니다.
        btnOrder.setOnClickListener {
            // 나를 감싸고 있는 부모 스케치북(Activity)을 불러와서,
            // 그 스케치북에 있는 하단 탭(bottom_nav)을 찾습니다.
            val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav)

            // 하단 탭의 현재 선택된 아이템을 '구매하기(tab_shop)'로 강제로 바꿔버립니다!
            bottomNav.selectedItemId = R.id.tab_shop
        }
    }
}