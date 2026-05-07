package com.example.nike

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nike.adapter.FollowingAdapter
import com.example.nike.data.network.ApiClient
import com.example.nike.data.repository.ProfileRepository
import com.example.nike.databinding.FragmentProfileBinding
import com.example.nike.viewmodel.ProfileViewModel
import com.example.nike.viewmodel.ProfileViewModelFactory

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val followingAdapter = FollowingAdapter(emptyList())
    private val viewModel: ProfileViewModel by viewModels {
        ProfileViewModelFactory(ProfileRepository(ApiClient.profileService))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
                Log.d("RETROFIT_CHECK", "데이터 수신 성공")

                binding.nickname.text = data.fullName

                Glide.with(this)
                    .load(data.avatar)
                    .circleCrop()
                    .into(binding.profileImage)

            }.onFailure { error ->
                Toast.makeText(context, "로드 실패: ${error.message}", Toast.LENGTH_SHORT).show()
                Log.e("RETROFIT", "Error: ${error.message}")
            }
        }
    }

    private fun observeUserList() {
        viewModel.userListResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess { list ->
                val newAdapter = FollowingAdapter(list)
                binding.followingList.adapter = newAdapter
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