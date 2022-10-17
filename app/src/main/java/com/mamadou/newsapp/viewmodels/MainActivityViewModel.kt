package com.mamadou.newsapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mamadou.newsapp.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainActivityViewModel() as T
        }
    }

    val isDownloadOverWifiOnly = App.prefsDataStore.isDownloadOverWifiOnly().asLiveData()

    fun toggleDownloadOverWifiOnly() {
        viewModelScope.launch(Dispatchers.IO) {
            App.prefsDataStore.toggleDownloadOverWifiOnly()
        }
    }

}