package com.example.nike.presentation.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nike.presentation.profile.FollowingAdapter
import com.example.nike.core.data.network.ApiClient
import com.example.nike.core.data.repository.ProfileRepository
import com.example.nike.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()
    private val followingAdapter = FollowingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeProfile()
        observeUserList()

        viewModel.fetchProfile("reqres_5193c564727d460caf02211006d13c9b")
        viewModel.fetchUserList("reqres_5193c564727d460caf02211006d13c9b")
    }

    private fun observeProfile() {
        viewModel.profileResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess { data ->
                binding.nickname.text = data.fullName
                Glide.with(this).load(data.avatar).circleCrop().into(binding.profileImage)
            }.onFailure { error ->
                Toast.makeText(context, "로드 실패: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeUserList() {
        viewModel.userListResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess { list ->
                followingAdapter.submitList(list)
            }.onFailure { error ->
                Log.e("RETROFIT", "리스트 로드 실패: ${error.message}")
            }
        }
    }

    private fun setupRecyclerView() {
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