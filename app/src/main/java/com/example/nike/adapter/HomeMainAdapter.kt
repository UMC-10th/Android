package com.example.nike.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.ProductData
import com.example.nike.databinding.ItemHomeHeaderBinding

// 부모 어댑터: 전체 화면(헤더)을 관리
class HomeMainAdapter(
    private val productList: MutableList<ProductData>,
    private val onVisitClicked: (ProductData) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // 뷰 타입 정의
    companion object {
        private const val TYPE_HEADER = 0
    }

    override fun getItemViewType(position: Int): Int {
        // 지금은 아이템이 헤더 1개뿐이므로 무조건 0 반환
        return TYPE_HEADER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // 1. 방금 만든 기차 1호차(item_home_header.xml)를 가져와서 뷰홀더 생성
        val binding = ItemHomeHeaderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HeaderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            // 2. 여기서! 네가 만들어둔 기존 ProductAdapter를 자식(가로) 리사이클러뷰에 연결해줌
            val childAdapter = ProductAdapter(
                productList = productList,
                screenType = ScreenType.HOME, // 명찰 추가
                onItemClicked = onVisitClicked // 이름 명시해서 전달
            )

            holder.binding.homeProductRv.apply {
                adapter = childAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
            }
        }
    }

    // 헤더(1호차) 딱 하나만 띄우면 되니까 1 반환
    override fun getItemCount(): Int = 1

    // 헤더용 뷰홀더
    class HeaderViewHolder(val binding: ItemHomeHeaderBinding) : RecyclerView.ViewHolder(binding.root)
}