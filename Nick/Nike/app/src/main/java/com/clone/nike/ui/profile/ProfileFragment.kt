package com.clone.nike.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.clone.nike.api.retrofit.ApiClient
import com.clone.nike.databinding.FragmentProfileBinding
import com.clone.nike.repository.repository.AuthRepositoryImpl
import com.clone.nike.ui.viewmodel.AuthViewModel
import com.clone.nike.ui.viewmodel.AuthViewModelFactory

class ProfileFragment: Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(
            AuthRepositoryImpl(
                ApiClient.authService
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.loadFollowing(1, "reqres_08962a9022be4a3499d4dbe5e1bbc482")
        authViewModel.loadProfile(1,"reqres_08962a9022be4a3499d4dbe5e1bbc482")

        authViewModel.name.observe(viewLifecycleOwner) { name ->
            binding.myPageNickNameTV.text = name
        }

        authViewModel.profileImg.observe(viewLifecycleOwner) { img ->
            Glide.with(requireContext())
                .load(img)
                .into(binding.myPageAvatarIV)
        }

        val adapter = FollowingRVAdapter()
        binding.myPageFollowingRV.adapter = adapter
        binding.myPageFollowingRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        authViewModel.followingImgs.observe(viewLifecycleOwner) { imgs ->
            adapter.setData(imgs)
        }
    }
}