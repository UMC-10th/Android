package com.example.nike.presentation.checkout.checkoutTabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nike.R
import com.example.nike.presentation.home.HomeAdapter
import com.example.nike.core.component.product.ProductAdapter
import com.example.nike.core.data.model.HomeData
import com.example.nike.core.data.PreferenceManager
import com.example.nike.core.component.product.ProductData
import com.example.nike.databinding.FragmentAllContentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllContentFragment : Fragment() {

    private var _binding: FragmentAllContentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AllContentViewModel by viewModels()
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter(
            onVisitClicked = { item ->
                Toast.makeText(context, "${item.name} 구매할까요?", Toast.LENGTH_SHORT).show()
            },
            onLikeClicked = { item ->
                viewModel.toggleLike(item.name)
            }
        )
        binding.checkoutAllProducts.apply {
            adapter = productAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun observeViewModel() {
        viewModel.productList.observe(viewLifecycleOwner) { list ->
            productAdapter.submitList(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}