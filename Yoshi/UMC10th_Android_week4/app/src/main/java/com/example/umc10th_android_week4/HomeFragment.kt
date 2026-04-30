package com.example.umc10th_android_week4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc10th_android_week4.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var productStorage: ProductStorage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productStorage = ProductStorage(requireContext())

        lifecycleScope.launch {
            var productList = productStorage.getProducts().first().toMutableList()

            // 최초 진입 시 더미 데이터 저장
            if (productList.isEmpty()) {
                productList = mutableListOf(
                    ProductData(R.drawable.ic_launcher_background, "Air Jordan XXXVI", "Basketball Shoes", "3 Colours", "US\$185"),
                    ProductData(R.drawable.ic_launcher_background, "Air Jordan 1 Mid", "Shoes", "1 Colour", "US\$125"),
                    ProductData(R.drawable.ic_launcher_background, "Nike Air Force 1 '07", "Women's Shoes", "5 Colours", "US\$115"),
                    ProductData(R.drawable.ic_launcher_background, "Nike Everyday Plus Cushioned", "Training Ankle Socks", "5 Colours", "US\$10"),
                    ProductData(R.drawable.ic_launcher_background, "Nike Elite Crew", "Basketball Socks", "7 Colours", "US\$16"),
                    ProductData(R.drawable.ic_launcher_background, "Jordan ENike Air Force", "Men's Shoes", "2 Colours", "US\$115")
                )
                productStorage.saveProducts(productList)
            }

            val adapter = ProductAdapter(productList) { position, product ->
                lifecycleScope.launch {
                    productList[position] = product
                    productStorage.saveProducts(productList)
                }
            }

            binding.rvHomeProduct.adapter = adapter
            binding.rvHomeProduct.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}