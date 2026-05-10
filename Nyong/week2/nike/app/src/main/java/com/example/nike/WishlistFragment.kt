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
import com.example.nike.databinding.FragmentWishlistBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    // Hilt가 WishlistViewModel을 자동으로 연결해줌
    private val viewModel: WishlistViewModel by viewModels()

    private lateinit var shoeAdapter: ShoeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeUiState()
    }

    private fun setupRecyclerView() {
        // 처음에는 빈 리스트로 Adapter 생성
        shoeAdapter = ShoeAdapter(arrayListOf()) { clickedPosition ->
            // 위시리스트에서 하트 클릭 시 ViewModel에 전달
            viewModel.unlikeFromWishlist(clickedPosition)
        }

        binding.rvWishlist.adapter = shoeAdapter
        binding.rvWishlist.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    shoeAdapter.updateShoes(state.wishlistShoes)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}