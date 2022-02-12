package com.nilcire.dreamscometrue.feature.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.nilcire.dreamscometrue.databinding.FragmentChatBinding
import java.lang.Exception
import kotlin.collections.LinkedHashMap

class ChatFragment: Fragment() {


    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ChatViewModel by lazy {
        ViewModelProvider(this, ChatViewModelFactory()).get(ChatViewModel::class.java)
    }

    companion object {
        // pair of tag name and viewpager fragment
        private val viewPagerTabAndContentData: LinkedHashMap<String, Class<out Fragment>> =
            linkedMapOf(
                "Friends" to FriendListFragment::class.java,
                "Groups" to ChatFragment::class.java
            )
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

        binding.vp2ChatContent.adapter = ChatViewPagerAdapter(this, viewPagerTabAndContentData)

        TabLayoutMediator(binding.tlTabs, binding.vp2ChatContent) {tab, position ->
            tab.text = viewPagerTabAndContentData.keys.elementAt(position)
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class ChatViewPagerAdapter(fragment: Fragment, private val listFragment: LinkedHashMap<String, Class<out Fragment>>): FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int {
            return listFragment.size
        }

        override fun createFragment(position: Int): Fragment {
            val key = listFragment.keys.elementAt(position)
            return try {
                listFragment[key]!!.newInstance()
            } catch (e: Exception) {
                Fragment()
            }
        }

    }


}