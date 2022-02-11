package com.nilcire.dreamscometrue.feature.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nilcire.dreamscometrue.data.SimpleUser

class FriendListViewModel: ViewModel() {

    private var _recentList = MutableLiveData<MutableList<SimpleUser>>()
    val recentList get() = _recentList

    private var _allFriendList = MutableLiveData<MutableList<SimpleUser>>()
    val allFriendList get() = _allFriendList

    init {
        _recentList.value = mutableListOf<SimpleUser>(
            SimpleUser("0001", "ONE", ""),
            SimpleUser("0003", "THREE", ""),
            SimpleUser("0005", "FIVE", ""),
            SimpleUser("0002", "TWO", ""),
            SimpleUser("0004", "FOUR", ""),
        )

        _allFriendList.value = mutableListOf<SimpleUser>(
            SimpleUser("0001", "ONE", ""),
            SimpleUser("0002", "TWO", ""),
            SimpleUser("0003", "THREE", ""),
            SimpleUser("0004", "FOUR", ""),
            SimpleUser("0005", "FIVE", ""),
            SimpleUser("0006", "SIX", ""),
            SimpleUser("0007", "SEVEN", ""),
            SimpleUser("0008", "EIGHT", ""),
            SimpleUser("0009", "NINE", ""),
        )
    }

}

class FriendListViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendListViewModel::class.java)) {
            return FriendListViewModel() as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}