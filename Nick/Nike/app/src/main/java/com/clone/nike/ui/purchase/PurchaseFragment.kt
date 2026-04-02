package com.clone.nike.ui.purchase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.clone.nike.R
import com.clone.nike.databinding.FragmentPurchaseBinding

class PurchaseFragment: Fragment(), GoodsRVOnclickListener {
    private lateinit var binding: FragmentPurchaseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPurchaseBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //더미 데이터
        val goodsDataList = mutableListOf(
            GoodsData(R.drawable.image_nike_everyday_plus_cushioned,"Nike Everyday Plus Cushioned","Traning Ankle Socks (6 Pairs)","5 Colours", "US$10",false),
            GoodsData(R.drawable.image_nike_everyday_plus_cushioned,"Nike Everyday Plus Cushioned","Traning Ankle Socks (6 Pairs)","5 Colours", "US$10",false),
            GoodsData(R.drawable.image_nike_everyday_plus_cushioned,"Nike Everyday Plus Cushioned","Traning Ankle Socks (6 Pairs)","5 Colours", "US$10",false),
            GoodsData(R.drawable.image_nike_everyday_plus_cushioned,"Nike Everyday Plus Cushioned","Traning Ankle Socks (6 Pairs)","5 Colours", "US$10",false)
        )

        //adapter 연결 (GridLayoutManager)
        val adapter = GoodsRVAdapter(goodsDataList,this)
        binding.purchaseGoodsRV.adapter = adapter
        binding.purchaseGoodsRV.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
    }

    //RV delegate pattern
    override fun wishOnclickListener(goods: GoodsData) {
        goods.isWished = !goods.isWished
    }

    //RV delegate pattern
    override fun goodsOnclickListener(goods: GoodsData) {
        findNavController().navigate(R.id.action_purchase_to_detail)
    }
}