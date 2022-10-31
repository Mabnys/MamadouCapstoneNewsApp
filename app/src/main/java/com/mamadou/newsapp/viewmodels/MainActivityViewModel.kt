package com.mamadou.newsapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mamadou.newsapp.preferences.PreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val prefsDataStore: PreferencesDataStore) : ViewModel() {
    val isDownloadOverWifiOnly = prefsDataStore.isDownloadOverWifiOnly().asLiveData()

    fun toggleDownloadOverWifiOnly() {
        viewModelScope.launch(Dispatchers.IO) {
            prefsDataStore.toggleDownloadOverWifiOnly()
        }
    }

}