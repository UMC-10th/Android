package com.example.nike

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.databinding.ItemShoeBinding

class ShoeAdapter(
    private val shoeList: ArrayList<ShoeData>,
    // 🌟 추가된 부분 1: 하트가 눌렸을 때 프래그먼트(사장님)에게 보고할 무전기(콜백 함수)
    private val onHeartClicked: (Int) -> Unit
) : RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder>() {

    // 1. 포장 직원: 상자에 진짜 데이터 갈아 끼우기
    inner class ShoeViewHolder(private val binding: ItemShoeBinding) : RecyclerView.ViewHolder(binding.root) {

        // 🌟 추가된 부분 2: 몇 번째 상자인지 알기 위해 position을 같이 받습니다.
        fun bind(shoe: ShoeData, position: Int) {
            binding.ivShoeImage.setImageResource(shoe.imageResId) // 이미지 세팅
            binding.tvShoeName.text = shoe.name                   // 이름 세팅
            binding.tvShoeSubtitle.text = shoe.subTitle           // 서브설명 세팅
            binding.tvShoeColors.text = shoe.colors               // 색상 수 세팅
            binding.tvShoePrice.text = shoe.price                 // 가격 세팅

            // 🌟 추가된 부분 3: 데이터(isLiked) 상태에 따라 하트 모양 갈아끼우기
            if (shoe.isLiked) {
                binding.btnHeart.setImageResource(R.drawable.ic_heart_on) // 꽉 찬 하트
            } else {
                binding.btnHeart.setImageResource(R.drawable.ic_heart_off) // 빈 하트
            }

            // 🌟 추가된 부분 4: 하트 버튼을 눌렀을 때 무전기에 대고 "사장님! N번째 신발 하트 눌렸습니다!" 하고 보고하기
            binding.btnHeart.setOnClickListener {
                onHeartClicked(position)
            }
        }
    }

    // 2. 빈 상자 만들기
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        val binding = ItemShoeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoeViewHolder(binding)
    }

    // 3. 상자에 데이터 넣으라고 직원에게 지시하기
    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        // 🌟 추가된 부분 5: 직원에게 포장 지시할 때, 몇 번째(position) 상자인지도 같이 알려주기
        holder.bind(shoeList[position], position)
    }

    // 4. 총 물량 보고
    override fun getItemCount(): Int = shoeList.size
}