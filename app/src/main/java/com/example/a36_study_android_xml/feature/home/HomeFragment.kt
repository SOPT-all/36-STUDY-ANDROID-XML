package com.example.a36_study_android_xml.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        bindingRcv()
    }

    private fun bindingRcv() {
        val contentsItem = listOf(
            HomeRcvContentsItem(image = R.drawable.img_home_contents_banner1, index = 1),
            HomeRcvContentsItem(image = R.drawable.img_home_contents_banner2, index = 2),
            HomeRcvContentsItem(image = R.drawable.img_home_contents_banner3, index = 3),
            HomeRcvContentsItem(image = R.drawable.img_home_contents_banner4, index = 4),
            HomeRcvContentsItem(image = R.drawable.img_home_contents_banner5, index = 5),
            HomeRcvContentsItem(image = R.drawable.img_home_contents_banner6, index = 6),
            HomeRcvContentsItem(image = R.drawable.img_home_contents_banner7, index = 7),
            HomeRcvContentsItem(image = R.drawable.img_home_contents_banner8, index = 8),
            HomeRcvContentsItem(image = R.drawable.img_home_contents_banner9, index = 9),
            HomeRcvContentsItem(image = R.drawable.img_home_contents_banner10, index = 10),
        )

        binding.homeTodayContentsRcv.adapter =
            HomeContentsItemAdapter(items = contentsItem, isIndexing = true)
        binding.homeNowContentsRcv.adapter =
            HomeContentsItemAdapter(items = contentsItem, isIndexing = false)
        binding.homePopularContentsRcv.adapter =
            HomeContentsItemAdapter(items = contentsItem, isIndexing = false)
    }

    private fun navigateToMy() {
        binding.homeProfileIv.setOnClickListener {
            (requireActivity() as? MainActivity)?.navigateToMy()
        }
    }
}