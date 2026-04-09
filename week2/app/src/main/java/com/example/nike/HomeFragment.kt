package com.example.nike

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nike.adapter.HomeAdapter
import com.example.nike.data.HomeData
import com.example.nike.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // splash title 받기
        val titleFromSplash = requireActivity().intent.getStringExtra("title")
        if (titleFromSplash != null){
            binding.homeTitle.text = titleFromSplash
        }

        // 최신 상품 목록
        val NewestItemsDataList = mutableListOf<HomeData>()

        NewestItemsDataList.add(HomeData(R.drawable.newest_item_1, "Air Jordan XXXVI", "US$185"))
        NewestItemsDataList.add(HomeData(R.drawable.newest_item_2, "Nike Air Force 1 '07", "US$115"))
        NewestItemsDataList.add(HomeData(R.drawable.newest_item_1, "Nike Air Max 270", "US$160"))
        NewestItemsDataList.add(HomeData(R.drawable.newest_item_2, "Air Jordan 1 Retro High OG", "US$180"))
        NewestItemsDataList.add(HomeData(R.drawable.newest_item_1, "Nike ZoomX Vaporfly Next% 2", "US$250"))
        NewestItemsDataList.add(HomeData(R.drawable.newest_item_2, "Nike Dunk Low Retro", "US$115"))
        NewestItemsDataList.add(HomeData(R.drawable.newest_item_1, "Nike Air VaporMax Plus", "US$210"))
        NewestItemsDataList.add(HomeData(R.drawable.newest_item_2, "Jordan Stay Loyal 2", "US$115"))
        NewestItemsDataList.add(HomeData(R.drawable.newest_item_1, "Nike Pegasus 40", "US$130"))
        NewestItemsDataList.add(HomeData(R.drawable.newest_item_2, "Nike Blazer Mid '77 Vintage", "US$105"))
        NewestItemsDataList.add(HomeData(R.drawable.newest_item_1, "Nike Air Force 1 React", "US$140"))
        NewestItemsDataList.add(HomeData(R.drawable.newest_item_2, "Air Jordan 12 Retro", "US$210"))

        val adapter = HomeAdapter(
            NewestItemsDataList,
            onVisitClicked = { newestItem ->
                Toast.makeText(context, "${newestItem.name}구매할까요?", Toast.LENGTH_SHORT).show()
            })

        binding.newestItemsRecyclerView.adapter = adapter
        binding.newestItemsRecyclerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}