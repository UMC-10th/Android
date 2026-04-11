package com.example.nike.viewholder

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.ProductData
import com.example.nike.R
import com.example.nike.adapter.ScreenType
import com.example.nike.databinding.ItemProductBinding

class ProductViewHolder(
    val binding: ItemProductBinding,
    private val screenType: ScreenType,
    private val onHeartClicked: ((ProductData) -> Unit)?,
    private val onItemClicked: ((ProductData) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: ProductData) {
        // 1. 무조건 보이는 기본 데이터
        binding.productTitleTv.text = data.name
        binding.productPriceTv.text = data.price
        binding.productIv.setImageResource(data.coverImg)

        // 2. 데이터가 있으면 켜고, 없으면 끄는(GONE) 선택적 데이터들
        if (!data.desc.isNullOrEmpty()) {
            binding.productDescTv.text = data.desc
            binding.productDescTv.visibility = View.VISIBLE
        } else {
            binding.productDescTv.visibility = View.GONE
        }

        if (!data.colors.isNullOrEmpty()) {
            binding.productColorsTv.text = data.colors
            binding.productColorsTv.visibility = View.VISIBLE
        } else {
            binding.productColorsTv.visibility = View.GONE
        }

        // 3. 화면 타입(명찰)에 따른 "하트"와 "베스트셀러 태그" 규칙 적용!
        when (screenType) {
            ScreenType.HOME -> {
                binding.productHeartIv.visibility = View.GONE
                // 홈에서는 필요 시 가격 색상 등을 다르게 처리
            }
            ScreenType.SHOP -> {
                binding.productHeartIv.visibility = View.VISIBLE

                // SHOP 화면에서만 BestSeller 태그 처리
                if (data.tag == "BestSeller") {
                    binding.productTagTv.text = data.tag
                    binding.productTagTv.visibility = View.VISIBLE
                } else {
                    binding.productTagTv.visibility = View.GONE
                }

                // 하트 상태
                if (data.isLiked) {
                    binding.productHeartIv.setImageResource(R.drawable.heart)
                } else {
                    binding.productHeartIv.setImageResource(R.drawable.emptyheart)
                }
            }
            ScreenType.WISHLIST -> {
                // 피그마 사진대로: 위시리스트엔 하트도 없고, 베스트셀러 태그도 없다!
                binding.productHeartIv.visibility = View.GONE
                binding.productTagTv.visibility = View.GONE
            }
        }

        // 클릭 이벤트 연결
        binding.productHeartIv.setOnClickListener { onHeartClicked?.invoke(data) }
        binding.root.setOnClickListener { onItemClicked?.invoke(data) }
    }
}