package com.example.nike // 본인 패키지명 확인

import androidx.recyclerview.widget.RecyclerView
import com.example.nike.databinding.ItemProductBinding
import com.example.nike.ProductData

// 1. 독립된 클래스로 선언 (inner 삭제!)
class ProductViewHolder(
    val binding: ItemProductBinding,
    private val onVisitClicked: (ProductData) -> Unit // 클릭 리스너를 인자로 받음
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductData) {
        // 데이터 연결
        binding.itemProductNameTv.text = product.name
        binding.itemProductPriceTv.text = product.price
        binding.itemProductImgIv.setImageResource(product.coverImg)

        // 클릭 이벤트 처리
        binding.root.setOnClickListener {
            onVisitClicked(product)
        }
    }
}