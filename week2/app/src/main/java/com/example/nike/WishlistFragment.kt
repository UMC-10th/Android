package com.example.nike

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.adapter.ProductAdapter
import com.example.nike.data.PreferenceManager
import com.example.nike.data.ProductData
import com.example.nike.databinding.FragmentWishlistBinding
import kotlinx.coroutines.launch

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceManager = PreferenceManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            preferenceManager.wishlistItemFlow.collect { wishlistItem ->
                setupRecyclerView(wishlistItem)
            }
        }
    }

    private fun setupRecyclerView(list: List<ProductData>) {
        val adapter = ProductAdapter(
            list,
            onVisitClicked = { wishlistItem ->
                Toast.makeText(context, "${wishlistItem.name} 사고싶어요", Toast.LENGTH_SHORT).show()
            },
            onLikeClicked = { item ->
                viewLifecycleOwner.lifecycleScope.launch {
                    preferenceManager.toggleLike(item.name)
                }
            })

        binding.wishlistRecyclerView.adapter = adapter
        binding.wishlistRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}