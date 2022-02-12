package com.nilcire.dreamscometrue.feature.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nilcire.dreamscometrue.data.SimpleUser
import com.nilcire.dreamscometrue.data.User

class ChatViewModel: ViewModel() {

    private var _friendList = MutableLiveData<SimpleUser>()
    val friendList: LiveData<SimpleUser> get() = _friendList

    private var _recentList = MutableLiveData<SimpleUser>()
    val recentList: LiveData<SimpleUser> get() = _recentList

}


class ChatViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            return ChatViewModel() as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}