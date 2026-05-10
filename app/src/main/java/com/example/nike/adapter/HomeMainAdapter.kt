package com.example.nike.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.data.model.ProductData
import com.example.nike.databinding.ItemHomeHeaderBinding

class HomeMainAdapter(
    private val productList: MutableList<ProductData>,
    private val onVisitClicked: (ProductData) -> Unit,
    private val onHeartClicked: (ProductData) -> Unit // 1. 하트 클릭 콜백 추가
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
    }

    // 2. 데이터 업데이트 함수 추가
    fun updateData(newList: List<ProductData>) {
        this.productList.clear()
        this.productList.addAll(newList)
        notifyDataSetChanged() // 헤더를 다시 바인딩하게 해서 자식 어댑터도 갱신되게 함
    }

    override fun getItemViewType(position: Int): Int = TYPE_HEADER

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemHomeHeaderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HeaderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            val childAdapter = ProductAdapter(
                productList = productList,
                screenType = ScreenType.HOME,
                onItemClicked = onVisitClicked,
                onHeartClicked = onHeartClicked // 3. 자식 어댑터에 하트 클릭 전달
            )

            holder.binding.homeProductRv.apply {
                adapter = childAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
            }
        }
    }

    override fun getItemCount(): Int = 1

    class HeaderViewHolder(val binding: ItemHomeHeaderBinding) : RecyclerView.ViewHolder(binding.root)
}