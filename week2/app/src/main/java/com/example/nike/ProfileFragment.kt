package com.example.nike

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.adapter.FollowingAdapter
import com.example.nike.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        val dummyData = listOf(
            R.drawable.following_1,
            R.drawable.following_2,
            R.drawable.following_3,
            R.drawable.following_4,
            R.drawable.following_5,
        )

        val followingAdapter = FollowingAdapter(dummyData)

        binding.followingList.apply {
            adapter = followingAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            isNestedScrollingEnabled = false
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}