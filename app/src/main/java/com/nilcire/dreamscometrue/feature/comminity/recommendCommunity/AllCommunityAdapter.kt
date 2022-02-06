package com.nilcire.dreamscometrue.feature.comminity.recommendCommunity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nilcire.dreamscometrue.data.Community
import com.nilcire.dreamscometrue.databinding.ViewholderAllCommunityBinding
import com.nilcire.dreamscometrue.feature.home.homeItems.HomePostCommentsAdapter

class AllCommunityAdapter: ListAdapter<Community, AllCommunityAdapter.CommunityViewHolder>(DiffCallback) {

    class CommunityViewHolder(private val binding: ViewholderAllCommunityBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindView(community: Community) {
            binding.tvRecommendName.text = community.name
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Community>() {
        override fun areItemsTheSame(oldItem: Community, newItem: Community): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Community, newItem: Community): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
        val binding = ViewholderAllCommunityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommunityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }


}