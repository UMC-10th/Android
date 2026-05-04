package com.clone.nike.ui.wish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.clone.nike.databinding.FragmentWishBinding
import com.clone.nike.ui.purchase.GoodsData
import com.clone.nike.repository.repository.DataStoreRepository
import kotlinx.coroutines.launch

class WishFragment: Fragment() {
    private lateinit var binding: FragmentWishBinding
    val GOODS_DATA = stringPreferencesKey("goods_data")
    private val repository by lazy {
        DataStoreRepository(requireContext())
    }

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
            repository.getGoodsInfo(GOODS_DATA).collect { goodsDataString ->
                val goodsDataList = repository.jsonToGson(goodsDataString)
                updateRV(goodsDataList)
            }
        }
    }

    fun updateRV(goodsDataList: MutableList<GoodsData>) {

        //goodsList에서 wishList로 변환
        val wishDataList = goodsDataList.filter { it.isWished }.toMutableList()

        //adapter연결 (GridLayoutManager)
        val adapter = WishRVAdapter(wishDataList, wishRVOnclickListener = { wishList ->
            val action = WishFragmentDirections.actionWishToDetail(wishList)
            findNavController().navigate(action)
        })
        binding.wishListRV.adapter = adapter
        binding.wishListRV.layoutManager = GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL,false)
    }
}