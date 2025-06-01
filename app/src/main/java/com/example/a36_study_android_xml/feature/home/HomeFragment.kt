package com.example.a36_study_android_xml.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.a36_study_android_xml.feature.my.MyFragment
import com.example.a36_study_android_xml.R
import com.example.a36_study_android_xml.databinding.FragmentHomeBinding
import com.example.a36_study_android_xml.feature.main.MainActivity

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToMy()
    }

    private fun navigateToMy() {
        binding.homeProfileIv.setOnClickListener {
            (requireActivity() as? MainActivity)?.navigateToMy()
        }
    }
}