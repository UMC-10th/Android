package com.example.nike.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.DataManager // 💡 본인 프로젝트 경로에 맞게 임포트!
import com.example.nike.ProductData
import com.example.nike.adapter.ProductAdapter
import com.example.nike.adapter.ScreenType
import com.example.nike.databinding.FragmentShopBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ShopFragment : Fragment() {
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    // 💡 DataManager 선언
    private lateinit var dataManager: DataManager
    private lateinit var shopAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // DataManager 초기화
        dataManager = DataManager(requireContext())

        // 어댑터 초기화 (처음엔 빈 리스트로 시작)
        shopAdapter = ProductAdapter(
            productList = mutableListOf(),
            screenType = ScreenType.SHOP,
            onHeartClicked = { product ->
                // 하트 버튼 누르면 DataStore 업데이트 함수 실행!
                toggleHeartStatus(product)
            },
            onItemClicked = { product ->
                Toast.makeText(requireContext(), "${product.name} 구매/상세 선택!", Toast.LENGTH_SHORT).show()
            }
        )

        binding.shopRecyclerview.apply {
            adapter = shopAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(false)
        }

        // 💡 DataStore 실시간 관찰! 데이터가 바뀌면 어댑터에 새 리스트 전달
        viewLifecycleOwner.lifecycleScope.launch {
            dataManager.getProducts().collect { products ->
                shopAdapter.updateData(products)
            }
        }
    }

    // 💡 하트 상태를 DataStore에 영구 저장하는 함수
    private fun toggleHeartStatus(product: ProductData) {
        viewLifecycleOwner.lifecycleScope.launch {
            // 현재 저장된 리스트 가져오기
            val currentList = dataManager.getProducts().first().toMutableList()

            // 내가 클릭한 아이템 찾기
            val index = currentList.indexOfFirst { it.name == product.name }
            if (index != -1) {
                // 하트 상태 반전 (true <-> false)
                currentList[index].isLiked = !currentList[index].isLiked

                // 다시 금고에 저장 (저장하면 위에 collect가 반응해서 화면 새로고침됨)
                dataManager.saveProducts(currentList)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}