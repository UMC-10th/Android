package com.clone.nike.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.clone.nike.databinding.FragmentProfileBinding
import com.clone.nike.ui.base.BaseFragment
import com.clone.nike.ui.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    private val authViewModel: AuthViewModel by viewModels()

    override fun initView() {
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