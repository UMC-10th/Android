package com.example.nike.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : ViewModel() {
    private val _navigateToCheckout = MutableLiveData<Boolean>()
    val navigateToCheckout: LiveData<Boolean> = _navigateToCheckout

    fun onOrderButtonClicked() {
        _navigateToCheckout.value = true
    }

    fun onNavigated() {
        _navigateToCheckout.value = false
    }
}