package com.nilcire.dreamscometrue.feature.chat

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nilcire.dreamscometrue.data.SimpleUser
import com.nilcire.dreamscometrue.databinding.FragmentFriendListBinding
import com.nilcire.dreamscometrue.databinding.ViewholderChatFriendBinding

class FriendListFragment: Fragment() {

    private var _binding: FragmentFriendListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FriendListViewModel by lazy {
        ViewModelProvider(this, FriendListViewModelFactory()).get(FriendListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFriendListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recentChatFriendAdapter = ChatFriendAdapter()
        binding.rvRecentChatFriend.adapter = recentChatFriendAdapter
        val allFriendAdapter = ChatFriendAdapter()
        binding.rvAllChatFriend.adapter = allFriendAdapter
        val searchFriendAdapter = ChatFriendAdapter()
        binding.rvSearchChatFriend.adapter = searchFriendAdapter

        viewModel.recentList.observe(viewLifecycleOwner, Observer {
            if (it.size == 0) return@Observer
            val showCount = if (it.size < 5 ) it.size else 5
            recentChatFriendAdapter.submitList(it.subList(0, showCount))
        })

        viewModel.allFriendList.observe(viewLifecycleOwner, Observer {
            if (it.size == 0) return@Observer
            allFriendAdapter.submitList(it)
        })

        viewModel.searchName.observe(viewLifecycleOwner, Observer { it ->
            if (it.isNullOrEmpty() || it.isBlank()) {
                searchFriendAdapter.submitList(mutableListOf<SimpleUser>())
            } else {
                viewModel.allFriendList.value?.let { allFriendList ->
                    if (allFriendList.count() > 0) {
                        viewModel.clearSearchList()
                        for (user in allFriendList) {
                            if (user.name.lowercase().contains(it)) {
                                viewModel.addSearchList(user)
                            }
                            searchFriendAdapter.submitList(viewModel.searchList.value)
                        }
                    } else {
                        searchFriendAdapter.submitList(mutableListOf<SimpleUser>())
                    }
                }
            }
        })

        binding.etFriendSearch.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.isNullOrEmpty() || p0.isBlank()) {
                    viewModel.updateSearchName("")
                } else {
                    viewModel.updateSearchName(p0.toString().lowercase())
                }
            }

        })

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }



    class ChatFriendAdapter: ListAdapter<SimpleUser, ChatFriendAdapter.ViewHolder>(DiffCallback) {
        class ViewHolder(private val binding: ViewholderChatFriendBinding): RecyclerView.ViewHolder(binding.root) {

            fun bindView(user: SimpleUser) {
                binding.tvChatFriendName.text = user.name
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = ViewholderChatFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindView(getItem(position))
        }

        companion object DiffCallback: DiffUtil.ItemCallback<SimpleUser>() {
            override fun areItemsTheSame(oldItem: SimpleUser, newItem: SimpleUser): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: SimpleUser, newItem: SimpleUser): Boolean {
                return oldItem.uuid == newItem.uuid
            }

        }
    }
}