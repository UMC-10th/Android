package com.example.nike

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val homeDummyList = arrayListOf(
        ShoeData(
            R.drawable.img_shoe_1,
            "Air Jordan XXXVI",
            "New Release",
            "1 Colour",
            "US$185",
            false
        ),
        ShoeData(
            R.drawable.img_shoe_2,
            "Nike Air Force 1",
            "Best Seller",
            "3 Colours",
            "US$115",
            false
        )
    )

    private val shopDummyList = arrayListOf(
        ShoeData(
            R.drawable.img_shoe_1,
            "Nike Everyday Plus Cushioned",
            "Training Ankle Socks",
            "5 Colours",
            "US$10",
            false
        ),
        ShoeData(
            R.drawable.img_shoe_2,
            "Nike Elite Crew",
            "Basketball Socks",
            "7 Colours",
            "US$16",
            false
        ),
        ShoeData(
            R.drawable.img_shoe_1,
            "Nike Air Force 1 '07",
            "Women's Shoes",
            "5 Colours",
            "US$115",
            false
        ),
        ShoeData(
            R.drawable.img_shoe_2,
            "Air Jordan 1 Mid",
            "Men's Shoes",
            "2 Colours",
            "US$125",
            false
        )
    )

    init {
        initializeAndObserveHomeShoes()
    }

    private fun initializeAndObserveHomeShoes() {
        viewModelScope.launch {
            localRepository.getHomeShoes().collect { savedHomeShoes ->

                if (savedHomeShoes.isEmpty()) {
                    // 앱 최초 실행처럼 홈 데이터가 비어 있으면 더미 데이터 저장
                    localRepository.saveHomeShoes(homeDummyList)

                    // 구매하기 데이터도 비어 있을 때를 대비해 초기 데이터 저장
                    localRepository.saveShopShoes(shopDummyList)

                    _uiState.update {
                        it.copy(homeShoes = homeDummyList)
                    }
                } else {
                    // 저장된 홈 데이터가 있으면 화면 상태에 반영
                    _uiState.update {
                        it.copy(homeShoes = savedHomeShoes)
                    }
                }
            }
        }
    }
}