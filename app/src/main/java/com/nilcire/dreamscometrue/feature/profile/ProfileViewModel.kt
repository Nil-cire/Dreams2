package com.nilcire.dreamscometrue.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProfileViewModel: ViewModel() {
}


class ProfileViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel() as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}