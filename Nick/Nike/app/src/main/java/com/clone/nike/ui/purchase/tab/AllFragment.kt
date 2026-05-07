package com.clone.nike.ui.purchase.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.clone.nike.databinding.FragmentPurchaseAllBinding
import com.clone.nike.ui.purchase.GoodsData
import com.clone.nike.ui.purchase.GoodsRVAdapter
import com.clone.nike.ui.purchase.GoodsRVOnclickListener
import com.clone.nike.ui.purchase.PurchaseFragmentDirections
import com.clone.nike.ui.purchase.SaveGoodsList
import com.clone.nike.repository.repository.DataStoreRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class AllFragment: Fragment(), GoodsRVOnclickListener, SaveGoodsList {

    private lateinit var binding: FragmentPurchaseAllBinding
    val GOODS_DATA = stringPreferencesKey("goods_data")

    private val repository by lazy {
        DataStoreRepository(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPurchaseAllBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            repository.getGoodsInfo(GOODS_DATA)
                .onStart {
                    //RV를 숨기고 ProgressBar를 표시해 로딩화면 구현
                    binding.purchaseProgressBarPB.visibility = View.VISIBLE
                    binding.purchaseGoodsRV.visibility = View.GONE
                }
                .catch {
                    binding.purchaseProgressBarPB.visibility = View.GONE
                    binding.purchaseGoodsRV.visibility = View.VISIBLE
                }
                .collect { goodsDataString ->
                    //로딩화면 구현을 위해 일부로 딜레이
                    delay(1000)

                    val goodsDataList = repository.jsonToGson(goodsDataString)
                    updateRV(goodsDataList)

                    binding.purchaseProgressBarPB.visibility = View.GONE
                    binding.purchaseGoodsRV.visibility = View.VISIBLE
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
            repository.saveGoodsInfo(goodsList, GOODS_DATA)
        }
    }

    fun updateRV(goodsDataList: MutableList<GoodsData>) {
        val adapter = GoodsRVAdapter(goodsDataList,this, this)
        binding.purchaseGoodsRV.adapter = adapter
        binding.purchaseGoodsRV.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
    }
}