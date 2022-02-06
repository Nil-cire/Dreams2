package com.nilcire.dreamscometrue.feature.comminity

import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Constraints
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nilcire.dreamscometrue.R
import com.nilcire.dreamscometrue.databinding.FragmentCommunityBinding
import com.nilcire.dreamscometrue.feature.comminity.recommendCommunity.AllCommunityAdapter
import com.nilcire.dreamscometrue.feature.comminity.recommendCommunity.RecommendCommunityAdapter

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CommunityFragment : Fragment() {

    private var _binding: FragmentCommunityBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CommunityViewModel by lazy {
        ViewModelProvider(this, CommunityViewModelFactory()).get(CommunityViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommunityBinding.inflate(inflater, container, false)

        val displayMetrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            this.activity?.window?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        }

        val width = displayMetrics.widthPixels
        val layoutParams: ConstraintLayout.LayoutParams =
            ConstraintLayout.LayoutParams(Constraints.LayoutParams.MATCH_CONSTRAINT, width*2/5)
        binding.rvRecommend.layoutParams = layoutParams

        val recommendAdapter = RecommendCommunityAdapter()
        binding.rvRecommend.adapter = recommendAdapter
        val allListAdapter = AllCommunityAdapter()
        binding.rvAllCommunity.adapter = allListAdapter

        viewModel.popularCommunities.observe(this.viewLifecycleOwner, Observer {
            binding.tvPopularTitle01.text = it[0].name
            binding.tvPopularTitle02.text = it[1].name
            binding.tvPopularTitle03.text = it[2].name
            binding.tvPopularTitle04.text = it[3].name
        })

        viewModel.recommendCommunities.observe(this.viewLifecycleOwner, Observer {
            recommendAdapter.submitList(it)
        })

        viewModel.allCommunities.observe(this.viewLifecycleOwner, Observer {
            allListAdapter.submitList(it)
        })

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}