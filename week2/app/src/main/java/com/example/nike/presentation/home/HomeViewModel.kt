package com.example.nike.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nike.core.data.model.HomeData
import com.example.nike.core.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _homeList = MutableLiveData<List<HomeData>>()
    val homeList: LiveData<List<HomeData>> = _homeList

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        viewModelScope.launch {
            repository.initHomeDataIfEmpty()
            repository.homeDataList.collect { list ->
                _homeList.postValue(list)
            }
        }
    }
}