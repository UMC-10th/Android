package com.example.nike

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nike.adapter.HomeAdapter
import com.example.nike.data.HomeData
import com.example.nike.data.PreferenceManager
import com.example.nike.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var preferenceManager: PreferenceManager

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

        preferenceManager = PreferenceManager(requireContext())

        // data store 저장
        viewLifecycleOwner.lifecycleScope.launch {
            preferenceManager.homeDataListFlow.collect { savedList ->
                if (savedList.isEmpty()) {
                    val dummyData = createHomeDummy()
                    preferenceManager.saveHomeDataList(dummyData)
                } else {
                    setupRecyclerView(savedList)
                }
            }
        }
    }

    private fun setupRecyclerView(list: List<HomeData>) {
        val adapter = HomeAdapter(
            list,
            onVisitClicked = { newestItem ->
                Toast.makeText(context, "${newestItem.name} 구매할까요?", Toast.LENGTH_SHORT).show()
            })

        binding.newestItemsRecyclerView.adapter = adapter
        binding.newestItemsRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
    }

    // 더미데이터
    private fun createHomeDummy(): List<HomeData> {
        return mutableListOf<HomeData>().apply {
            add(HomeData(R.drawable.newest_item_1, "Air Jordan XXXVI", "US$185"))
            add(HomeData(R.drawable.newest_item_2, "Nike Air Force 1 '07", "US$115"))
            add(HomeData(R.drawable.newest_item_1, "Nike Air Max 270", "US$160"))
            add(HomeData(R.drawable.newest_item_2, "Air Jordan 1 Retro High OG", "US$180"))
            add(HomeData(R.drawable.newest_item_1, "Nike ZoomX Vaporfly Next% 2", "US$250"))
            add(HomeData(R.drawable.newest_item_2, "Nike Dunk Low Retro", "US$115"))
            add(HomeData(R.drawable.newest_item_1, "Nike Air VaporMax Plus", "US$210"))
            add(HomeData(R.drawable.newest_item_2, "Jordan Stay Loyal 2", "US$115"))
            add(HomeData(R.drawable.newest_item_1, "Nike Pegasus 40", "US$130"))
            add(HomeData(R.drawable.newest_item_2, "Nike Blazer Mid '77 Vintage", "US$105"))
            add(HomeData(R.drawable.newest_item_1, "Nike Air Force 1 React", "US$140"))
            add(HomeData(R.drawable.newest_item_2, "Air Jordan 12 Retro", "US$210"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}