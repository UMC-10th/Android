package com.example.nike.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.ProductData
import com.example.nike.databinding.ItemShopBinding

class ShopViewHolder(
    val binding: ItemShopBinding,
    private val onItemClicked: (ProductData) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductData) {
        // 1. 이미지 연결 (ProductData의 coverImg 사용)
        binding.itemProductImgIv.setImageResource(product.coverImg)

        // 2. 텍스트 데이터 연결 (이름, 가격)
        binding.itemProductNameTv.text = product.name
        binding.itemProductPriceTv.text = product.price

        // 3. 설명 및 색상 (ProductData에 없으면 임시 텍스트, 있으면 연결)
        // 현재 ProductData 구조에 desc가 없다면 아래처럼 하드코딩하거나
        // 나중에 데이터를 확장했을 때 product.desc로 바꾸면 돼.
        binding.itemProductDescTv.text = "Nike Shoes"
        binding.itemProductColorsTv.text = "1 Colour"

        // 4. 베스트셀러 태그 로직 (중요!)
        // ProductData의 tag 값이 "BestSeller"일 때만 보여줌
        if (product.tag == "BestSeller") {
            binding.itemProductTagTv.visibility = View.VISIBLE
        } else {
            // GONE으로 설정해야 공간을 차지하지 않고 이름이 위로 붙음
            binding.itemProductTagTv.visibility = View.GONE
        }

        // 5. 아이템 전체 클릭 이벤트
        binding.root.setOnClickListener {
            onItemClicked(product)
        }

        // 6. 하트 클릭 이벤트 (필요 시 추가)
        binding.itemProductHeartIv.setOnClickListener {
            // 하트 버튼 눌렀을 때의 로직 (토스트 등)
        }
    }
}