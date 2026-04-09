package com.example.nike.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.ProductData
import com.example.nike.viewholder.ProductViewHolder
import com.example.nike.databinding.ItemProductBinding

class ProductAdapter(
    private var productList: MutableList<ProductData>,
    private val onVisitClicked: (ProductData) -> Unit
) : RecyclerView.Adapter<ProductViewHolder>() { // <--- 외부 클래스 지정


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        // 2. 뷰홀더를 만들 때 '클릭 리스너'도 같이 넘겨줌
        return ProductViewHolder(binding, onVisitClicked)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size
}