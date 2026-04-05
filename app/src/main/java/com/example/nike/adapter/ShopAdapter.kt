package com.example.nike.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.ProductData
import com.example.nike.databinding.ItemShopBinding
import com.example.nike.viewholder.ShopViewHolder

class ShopAdapter(
    private val itemList: ArrayList<ProductData>,
    private val onItemClick: (ProductData) -> Unit // 아이템 클릭 시 실행할 함수
) : RecyclerView.Adapter<ShopViewHolder>() {

    // 1. 뷰홀더를 처음 만드는 곳 (item_shop.xml을 가져와서 뷰홀더에 넣음)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val binding = ItemShopBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ShopViewHolder(binding, onItemClick)
    }

    // 2. 만들어진 뷰홀더에 실제 데이터를 채워넣는 곳
    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    // 3. 전체 아이템이 몇 개인지 알려주는 곳
    override fun getItemCount(): Int = itemList.size
}