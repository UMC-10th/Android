package com.clone.nike.ui.purchase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

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
}