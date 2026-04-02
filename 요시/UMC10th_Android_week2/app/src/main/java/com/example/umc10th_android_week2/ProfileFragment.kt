package com.example.umc10th_android_week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val btnEdit = view.findViewById<Button>(R.id.btn_edit_profile)

        btnEdit.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfileEditFragment())
                // 프로필 수정 화면에서 뒤로가기 누를시 프로필 페이지로 이동
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}