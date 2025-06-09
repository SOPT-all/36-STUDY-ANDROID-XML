package com.example.a36_study_android_xml.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a36_study_android_xml.databinding.HomeContentsRcvItemBinding
import com.example.a36_study_android_xml.feature.home.HomeContentsItemAdapter.HomeContentsItemViewHolder


class HomeContentsItemAdapter(
    private val items: List<HomeRcvContentsItem>,
    private val isIndexing: Boolean
) :
    RecyclerView.Adapter<HomeContentsItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeContentsItemViewHolder {
        val binding =
            HomeContentsRcvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeContentsItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HomeContentsItemViewHolder,
        position: Int
    ) {
        holder.bind(item = items[position], index = position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class HomeContentsItemViewHolder(private val binding: HomeContentsRcvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeRcvContentsItem, index: Int) {
            binding.homeContentsRcvItemIv.setImageResource(item.image)

            if (isIndexing) {
                binding.homeContentsRcvItemTv.visibility = View.VISIBLE
                binding.homeContentsRcvItemTv.text = item.index.toString()
            }
        }
    }
}