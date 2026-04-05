package com.clone.nike.ui.purchase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.clone.nike.databinding.FragmentDetailBinding

class GoodsDetail: Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: GoodsDetailArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val goodsInfo = args.goodsInfo
        binding.apply {
            detailTitleBackIV.setOnClickListener {
                findNavController().popBackStack()
            }

            detailTitleGoodsNameTV.text = goodsInfo.goodsName
            detailGoodsNameTV.text = goodsInfo.goodsName

            detailGoodsIV.setImageResource(goodsInfo.goodsImgResId)
            detailGoodsPriceTV.text = goodsInfo.goodsPrice
            detailCategoryTV.text = goodsInfo.category
        }
    }
}