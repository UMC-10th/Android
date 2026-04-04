package com.example.nike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController // 중요! 네비게이션용 임포트
import com.example.nike.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    // 1. 바인딩 객체 선언
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 2. 지도를 펼쳐서 내 화면(XML) 가져오기
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 3. 장바구니의 '주문하기' 버튼을 찾아서 클릭 이벤트를 달아주기
        binding.orderButton.setOnClickListener {

            // 4. NavController에게 명령을 내립니다

            findNavController().navigate(R.id.action_cartFragment_to_shopFragment)
        }
    }

    // 5. 프래그먼트가 사라질 때 메모리 청소를 해줍니다
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}