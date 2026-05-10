package com.example.nike.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nike.DataManager
import com.example.nike.data.model.ProductData
import com.example.nike.R
import com.example.nike.adapter.HomeMainAdapter
import com.example.nike.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var dataManager: DataManager
    private lateinit var mainAdapter: HomeMainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. DataManager 초기화 (금고 관리자)
        dataManager = DataManager(requireContext())

        // 2. 어댑터 초기화 (하트 클릭 시 동작할 로직을 람다로 전달)
        mainAdapter = HomeMainAdapter(
            productList = mutableListOf(),
            onVisitClicked = { product ->
                // 구매하기/상세 페이지 이동 로직 (필요 시 구현)
            },
            onHeartClicked = { clickedProduct ->
                // 하트 버튼 눌렀을 때 실행될 함수 호출
                updateHeartStatus(clickedProduct)
            }
        )

        // 3. 리사이클러뷰 설정
        binding.homeMainRv.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        // 4. DataStore 실시간 관찰 및 데이터 로드
        // viewLifecycleOwner.lifecycleScope를 써야 안전하게 비동기 처리가 가능해!
        viewLifecycleOwner.lifecycleScope.launch {
            dataManager.getProducts().collect { products ->
                if (products.isEmpty()) {
                    saveInitialDummyData() // 최초 1회만 실행됨
                } else {
                    mainAdapter.updateData(products) // 모든 변화에 대응
                }
            }
        }
    }

    // [미션 추가] 최초 더미 데이터를 DataStore에 저장하는 함수
    private fun saveInitialDummyData() {
        val dummyList = listOf(
            ProductData(
                "Nike Air Force 1 '07 (White)",
                "US$115",
                R.drawable.air_jordan,
                "BestSeller"
            ),
            ProductData("Nike Everyday Plus (Pack A)", "US$10", R.drawable.air_jordan, ""),
            ProductData("Jordan ENike Air Force", "US$115", R.drawable.air_jordan, "BestSeller"),
            ProductData("Nike Elite Crew (Black)", "US$16", R.drawable.air_jordan, ""),
            ProductData(
                "Nike Everyday Plus (Pack B)",
                "US$10",
                R.drawable.air_jordan,
                "",
                "Training Ankle Socks",
                "5 Colours"
            ),
            ProductData("Nike Dunk Low (Retro)", "US$110", R.drawable.air_jordan, ""),
            ProductData("Nike Air Max (97)", "US$130", R.drawable.air_jordan, "BestSeller"),
            ProductData(
                "Nike Everyday Plus (Pack C)",
                "US$10",
                R.drawable.air_jordan,
                "",
                "Training Ankle Socks",
                "5 Colours"
            )
        )
        viewLifecycleOwner.lifecycleScope.launch {
            dataManager.saveProducts(dummyList)
            // 저장하는 순간 위의 collect가 반응해서 자동으로 화면이 그려질 거야!
        }
    }

    // [미션 추가] 하트 클릭 시 DataStore의 데이터를 수정하는 함수
    private fun updateHeartStatus(product: ProductData) {
        viewLifecycleOwner.lifecycleScope.launch {
            // 1. 현재 DataStore에 저장된 최신 리스트를 가져옴
            val currentList = dataManager.getProducts().first().toMutableList()

            // 2. 클릭한 상품이 리스트의 몇 번째에 있는지 확인
            val index = currentList.indexOfFirst { it.name == product.name }

            if (index != -1) {
                // 3. 해당 상품의 하트 상태를 반전시킴
                currentList[index].isLiked = !currentList[index].isLiked

                // 4. 수정된 전체 리스트를 DataStore에 다시 저장 (덮어쓰기)
                dataManager.saveProducts(currentList)
            }
        }
    }
}