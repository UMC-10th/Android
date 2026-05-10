package com.example.nike

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.databinding.ItemShoeBinding

class ShoeAdapter(
    private var shoeList: ArrayList<ShoeData>,
    // 하트가 눌렸을 때 Fragment에게 몇 번째 상품인지 알려주는 콜백 함수
    private val onHeartClicked: (Int) -> Unit
) : RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder>() {

    // ViewModel에서 새로운 상품 목록이 오면 Adapter 데이터 갱신
    fun updateShoes(newShoes: ArrayList<ShoeData>) {
        shoeList = newShoes
        notifyDataSetChanged()
    }

    // 1. 포장 직원: 상자에 진짜 데이터 갈아 끼우기
    inner class ShoeViewHolder(
        private val binding: ItemShoeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        // 몇 번째 상자인지 알기 위해 position을 같이 받음
        fun bind(shoe: ShoeData, position: Int) {
            binding.ivShoeImage.setImageResource(shoe.imageResId)
            binding.tvShoeName.text = shoe.name
            binding.tvShoeSubtitle.text = shoe.subTitle
            binding.tvShoeColors.text = shoe.colors
            binding.tvShoePrice.text = shoe.price

            // 데이터(isLiked) 상태에 따라 하트 이미지 변경
            if (shoe.isLiked) {
                binding.btnHeart.setImageResource(R.drawable.ic_heart_on)
            } else {
                binding.btnHeart.setImageResource(R.drawable.ic_heart_off)
            }

            // 하트 버튼 클릭 시 몇 번째 상품인지 ViewModel에 전달할 수 있도록 콜백 호출
            binding.btnHeart.setOnClickListener {
                onHeartClicked(position)
            }
        }
    }

    // 2. 빈 상자 만들기
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        val binding = ItemShoeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ShoeViewHolder(binding)
    }

    // 3. 상자에 데이터 넣으라고 직원에게 지시하기
    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        holder.bind(shoeList[position], position)
    }

    // 4. 총 물량 보고
    override fun getItemCount(): Int = shoeList.size
}