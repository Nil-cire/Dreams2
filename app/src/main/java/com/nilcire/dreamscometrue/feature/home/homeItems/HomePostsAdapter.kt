package com.nilcire.dreamscometrue.feature.home.homeItems

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nilcire.dreamscometrue.R
import com.nilcire.dreamscometrue.data.HomePost
import com.nilcire.dreamscometrue.databinding.ViewholderHomePostBinding
import com.nilcire.dreamscometrue.util.EllipsisTextView
import com.nilcire.dreamscometrue.util.ViewUtil

class HomePostsAdapter(private val context: Context) : ListAdapter<HomePost, HomePostsAdapter.HomePostsViewHolder>(DiffCallback) {

    class HomePostsViewHolder(private val binding: ViewholderHomePostBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: HomePost) {
            binding.tvPostTitle.text = post.title
            binding.tvContent.text = post.content
            binding.tvPostTime.text = post.postTimeStamp.toString()
            binding.tvUserName.text = post.poster.name

            Glide
                .with(context)
                .load(post.imageUrl)
                .centerCrop()
                .placeholder(context.getDrawable(R.drawable.ic_account_24))
                .into(binding.imPostImage)

            // view about comments
            val commentAdapter = HomePostCommentsAdapter()
            binding.rvComments.adapter = commentAdapter
            if (post.comments.size >= commentAdapter.displayCount) {
                commentAdapter.submitList(post.comments.subList(0, commentAdapter.displayCount))
            }

            if (commentAdapter.displayCount >= post.comments.size) {
                binding.tvMoreComment.visibility = View.GONE
            }

            binding.tvMoreComment.setOnClickListener {
                commentAdapter.displayCount += 1
                if (post.comments.size >= commentAdapter.displayCount) {
                    commentAdapter.submitList(post.comments.subList(0, commentAdapter.displayCount))
                }

                if (commentAdapter.displayCount >= post.comments.size) {
                    binding.tvMoreComment.visibility = View.GONE
                }
            }

            // view about content
            binding.tvContent.setOnLayoutListener(object: EllipsisTextView.OnLayoutListener{
                override fun onLayout() {
                    updateCommentEllipse()
                }
            })
        }



        fun updateCommentEllipse() {
            if (ViewUtil.isTextViewEllipse(binding.tvContent)) {
                Log.d("TAG", "updateView: isEllipsis")
                binding.tvSeeMoreContent.visibility = View.VISIBLE

                binding.tvSeeMoreContent.setOnClickListener {
                    binding.tvContent.maxLines += 10

                    if (!ViewUtil.isTextViewEllipse(binding.tvContent)) {
                        binding.tvSeeMoreContent.visibility = View.GONE
                    }
                }

            } else {
                binding.tvSeeMoreContent.visibility = View.GONE
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePostsViewHolder {
        val binding = ViewholderHomePostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomePostsViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: HomePostsViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.updateCommentEllipse()
    }

    companion object DiffCallback: DiffUtil.ItemCallback<HomePost>() {
        override fun areItemsTheSame(oldItem: HomePost, newItem: HomePost): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: HomePost, newItem: HomePost): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }
}