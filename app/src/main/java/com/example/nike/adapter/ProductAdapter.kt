package com.example.nike.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.ProductData
import com.example.nike.databinding.ItemProductBinding
import com.example.nike.ProductViewHolder // 💡 밖에 있는 뷰홀더를 불러옴!

// 💡 Enum 클래스는 어댑터 패키지에 그대로 둠
enum class ScreenType { HOME, SHOP, WISHLIST }

class ProductAdapter(
    private var productList: MutableList<ProductData>,
    private val screenType: ScreenType,
    private val onHeartClicked: ((ProductData) -> Unit)? = null,
    private val onItemClicked: ((ProductData) -> Unit)? = null
// 💡 주의: <ProductAdapter.ProductViewHolder>가 아니라, 밖에 있는 <ProductViewHolder>를 적어야 함!
) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        // 분리된 뷰홀더 클래스에 데이터 뭉치를 넘겨주면서 생성!
        return ProductViewHolder(binding, screenType, onHeartClicked, onItemClicked)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    fun updateData(newList: List<ProductData>) {
        this.productList.clear()
        this.productList.addAll(newList)
        notifyDataSetChanged()
    }
}