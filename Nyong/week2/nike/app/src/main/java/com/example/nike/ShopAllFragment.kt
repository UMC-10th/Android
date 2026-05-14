package com.example.nike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.databinding.FragmentShopAllBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShopAllFragment : Fragment() {

    private var _binding: FragmentShopAllBinding? = null
    private val binding get() = _binding!!

    // Hilt가 ShopViewModel을 자동으로 생성해서 연결해줌
    private val viewModel: ShopViewModel by viewModels()

    // Adapter를 한 번만 만들어서 계속 재사용
    private lateinit var shoeAdapter: ShoeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 화면이 완전히 만들어진 뒤 실행
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeUiState()
    }

    private fun setupRecyclerView() {
        // 처음에는 빈 리스트로 Adapter 생성
        shoeAdapter = ShoeAdapter(arrayListOf()) { clickedPosition ->
            // 하트 클릭 이벤트만 ViewModel에 전달
            viewModel.toggleLike(clickedPosition)
        }

        binding.rvShopAll.adapter = shoeAdapter
        binding.rvShopAll.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    // ViewModel의 상품 목록 상태를 관찰해서 RecyclerView 갱신
                    shoeAdapter.updateShoes(state.shopShoes)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}