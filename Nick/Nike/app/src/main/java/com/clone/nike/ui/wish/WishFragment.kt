package com.clone.nike.ui.wish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.clone.nike.R
import com.clone.nike.databinding.FragmentWishBinding
import com.clone.nike.ui.purchase.GoodsData

class WishFragment: Fragment() {
    private lateinit var binding: FragmentWishBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWishBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wishList = mutableListOf(
            WishListData(R.drawable.image_nike_everyday_plus_cushioned,"Nike Everyday Plus Cushioned","Traning Ankle Socks (6 Pairs)","5 Colours", "US$10"),
            WishListData(R.drawable.image_nike_everyday_plus_cushioned,"Nike Everyday Plus Cushioned","Traning Ankle Socks (6 Pairs)","5 Colours", "US$10")
        )

        val adapter = WishRVAdapter(wishList)
        binding.wishListRV.adapter = adapter
        binding.wishListRV.layoutManager = GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL,false)
    }
}