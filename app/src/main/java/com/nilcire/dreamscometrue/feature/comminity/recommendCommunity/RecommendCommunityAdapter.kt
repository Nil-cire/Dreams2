package com.nilcire.dreamscometrue.feature.comminity.recommendCommunity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nilcire.dreamscometrue.data.Community
import com.nilcire.dreamscometrue.databinding.ViewholderRecommendCommunityBinding

class RecommendCommunityAdapter:
    ListAdapter<Community, RecommendCommunityAdapter.RecommendCommunityViewHolder>(DiffCallback) {

    class RecommendCommunityViewHolder(private val binding: ViewholderRecommendCommunityBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindView(community: Community) {
            binding.tvRecommendName.text = community.name
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendCommunityViewHolder {
        val binding = ViewholderRecommendCommunityBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return RecommendCommunityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendCommunityViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }


    companion object DiffCallback: DiffUtil.ItemCallback<Community>() {
        override fun areItemsTheSame(oldItem: Community, newItem: Community): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Community, newItem: Community): Boolean {
            return oldItem.uuid == newItem.uuid
        }

    }
}