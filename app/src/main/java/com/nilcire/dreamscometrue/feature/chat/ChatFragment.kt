package com.nilcire.dreamscometrue.feature.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.nilcire.dreamscometrue.data.SimpleUser
import com.nilcire.dreamscometrue.databinding.FragmentChatBinding
import com.nilcire.dreamscometrue.databinding.ViewholderChatFriendBinding

class ChatFragment: Fragment() {


    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ChatViewModel by lazy {
        ViewModelProvider(this, ChatViewModelFactory()).get(ChatViewModel::class.java)
    }


    companion object {
        private val tabNames = listOf<String>("Friends", "Groups")
        private val viewPagerFragments = listOf(FriendListFragment::class.java, FriendListFragment::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vp2ChatContent.adapter = ChatViewPagerAdapter(this, tabNames)

        TabLayoutMediator(binding.tlTabs, binding.vp2ChatContent) {tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class ChatViewPagerAdapter(fragment: Fragment, private val listFragment: List<String>): FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int {
            return listFragment.size
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> FriendListFragment()
                1 -> FriendListFragment()
                else -> throw IllegalArgumentException("Index without support fragment")
            }
        }

    }


}