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

        viewModel.fetchProfile("reqres_5193c564727d460caf02211006d13c9b")
    }

    private fun observeProfile() {
        viewModel.profileResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess { data ->
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