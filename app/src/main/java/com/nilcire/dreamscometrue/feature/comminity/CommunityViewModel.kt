package com.nilcire.dreamscometrue.feature.comminity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nilcire.dreamscometrue.data.Community
import com.nilcire.dreamscometrue.data.SimpleUser

class CommunityViewModel: ViewModel() {
    private var _popularCommunities = MutableLiveData<MutableList<Community>>()
    val popularCommunities get() = _popularCommunities

    private var _recommendCommunities = MutableLiveData<MutableList<Community>>()
    val recommendCommunities get() = _recommendCommunities

    private var _allCommunities = MutableLiveData<MutableList<Community>>()
    val allCommunities get() = _allCommunities

    init {
        _popularCommunities.value = mutableListOf<Community>(
            Community("0000", "First", "", SimpleUser("", "name", ""), mutableListOf()),
            Community("0001", "Second", "", SimpleUser("", "name", ""), mutableListOf()),
            Community("0003", "Third", "", SimpleUser("", "name", ""), mutableListOf()),
            Community("0004", "Fourth", "", SimpleUser("", "name", ""), mutableListOf()),
        )

        _recommendCommunities.value = mutableListOf<Community>(
            Community("0000", "First", "", SimpleUser("", "name", ""), mutableListOf()),
            Community("0001", "Second", "", SimpleUser("", "name", ""), mutableListOf()),
            Community("0003", "Third", "", SimpleUser("", "name", ""), mutableListOf()),
            Community("0004", "Fourth", "", SimpleUser("", "name", ""), mutableListOf()),
            Community("0005", "Fifth", "", SimpleUser("", "name", ""), mutableListOf()),
        )

        _allCommunities.value = mutableListOf<Community>(
            Community("0001", "First", "", SimpleUser("", "name", ""), mutableListOf()),
            Community("0002", "Second", "", SimpleUser("", "name", ""), mutableListOf()),
            Community("0003", "Third", "", SimpleUser("", "name", ""), mutableListOf()),
            Community("0004", "Fourth", "", SimpleUser("", "name", ""), mutableListOf()),
            Community("0005", "Fifth", "", SimpleUser("", "name", ""), mutableListOf()),
            Community("0006", "Fifth", "", SimpleUser("", "name", ""), mutableListOf()),
            Community("0007", "Fifth", "", SimpleUser("", "name", ""), mutableListOf()),
            Community("0008", "Fifth", "", SimpleUser("", "name", ""), mutableListOf()),
            Community("0009", "Fifth", "", SimpleUser("", "name", ""), mutableListOf()),
            Community("0010", "Fifth", "", SimpleUser("", "name", ""), mutableListOf()),
            Community("0011", "Fifth", "", SimpleUser("", "name", ""), mutableListOf()),
            Community("0012", "Fifth", "", SimpleUser("", "name", ""), mutableListOf()),
            Community("0013", "Fifth", "", SimpleUser("", "name", ""), mutableListOf()),
        )
    }
}

class CommunityViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommunityViewModel::class.java)) {
            return CommunityViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}