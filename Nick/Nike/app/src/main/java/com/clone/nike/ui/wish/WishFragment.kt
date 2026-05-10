package com.clone.nike.ui.wish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.clone.nike.databinding.FragmentWishBinding
import com.clone.nike.ui.purchase.GoodsData
import com.clone.nike.ui.viewmodel.WishViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WishFragment: Fragment() {
    private lateinit var binding: FragmentWishBinding
    private val viewModel: WishViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWishBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                updateRV(state.goodsDataList.toMutableList())
            }
        }
    }

    fun updateRV(goodsDataList: MutableList<GoodsData>) {
        val wishDataList = viewModel.listFilter(goodsDataList)

        //adapter연결 (GridLayoutManager)
        val adapter = WishRVAdapter(wishDataList, wishRVOnclickListener = { wishList ->
            val action = WishFragmentDirections.actionWishToDetail(wishList)
            findNavController().navigate(action)
        })
        binding.wishListRV.adapter = adapter
        binding.wishListRV.layoutManager = GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL,false)
    }
}