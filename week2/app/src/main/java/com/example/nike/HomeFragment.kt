package com.example.nike

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}