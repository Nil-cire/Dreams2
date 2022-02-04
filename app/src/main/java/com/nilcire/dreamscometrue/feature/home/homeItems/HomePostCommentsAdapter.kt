package com.nilcire.dreamscometrue.feature.home.homeItems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nilcire.dreamscometrue.data.Comment
import com.nilcire.dreamscometrue.databinding.ViewholderHomePostCommentBinding

class HomePostCommentsAdapter: ListAdapter<Comment, HomePostCommentsAdapter.CommentViewHolder>(DiffCallback) {

    var displayCount = 1

    class CommentViewHolder(private val binding: ViewholderHomePostCommentBinding): RecyclerView.ViewHolder(binding.root) {

            fun bind(comment: Comment) {
                val fullMessage = comment.poster.name + ": " +comment.content
                binding.tvComment.text = fullMessage
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ViewholderHomePostCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.content == newItem.content
        }

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

}