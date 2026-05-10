package com.clone.nike.ui.purchase.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.clone.nike.databinding.FragmentPurchaseAllBinding
import com.clone.nike.ui.base.BaseFragment
import com.clone.nike.ui.purchase.GoodsData
import com.clone.nike.ui.purchase.GoodsRVAdapter
import com.clone.nike.ui.purchase.GoodsRVOnclickListener
import com.clone.nike.ui.purchase.PurchaseFragmentDirections
import com.clone.nike.ui.purchase.SaveGoodsList
import com.clone.nike.ui.viewmodel.PurchaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllFragment: BaseFragment<FragmentPurchaseAllBinding>(FragmentPurchaseAllBinding::inflate), GoodsRVOnclickListener, SaveGoodsList {
    private val viewModel: PurchaseViewModel by viewModels()

    override fun initView() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                if (state.isLoading) {
                    binding.purchaseProgressBarPB.visibility = View.VISIBLE
                    binding.purchaseGoodsRV.visibility = View.GONE
                } else {
                    updateRV(state.goodsDataList.toMutableList())

                    binding.purchaseProgressBarPB.visibility = View.GONE
                    binding.purchaseGoodsRV.visibility = View.VISIBLE
                }
            }
        }
    }

    //RV delegate pattern
    override fun wishOnclickListener(goods: GoodsData) {
        goods.isWished = !goods.isWished
    }

    //RV delegate pattern
    override fun goodsOnclickListener(goods: GoodsData) {
        val action = PurchaseFragmentDirections.actionPurchaseToDetail(
            goodsInfo = goods
        )
        findNavController().navigate(action)
    }

    override fun onGoodsListChanged(goodsList: MutableList<GoodsData>) {
        lifecycleScope.launch {
            viewModel.saveGoodsInfo(goodsList)
        }
    }

    fun updateRV(goodsDataList: MutableList<GoodsData>) {
        val adapter = GoodsRVAdapter(goodsDataList,this, this)
        binding.purchaseGoodsRV.adapter = adapter
        binding.purchaseGoodsRV.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
    }
}