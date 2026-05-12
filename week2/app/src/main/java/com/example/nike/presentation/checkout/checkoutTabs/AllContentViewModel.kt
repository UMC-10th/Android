package com.example.nike.presentation.checkout.checkoutTabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nike.core.component.product.ProductData
import com.example.nike.core.data.repository.CheckoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllContentViewModel @Inject constructor(
    private val repository: CheckoutRepository
) : ViewModel() {

    private val _productList = MutableLiveData<List<ProductData>>()
    val productList: LiveData<List<ProductData>> = _productList

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            repository.initCheckoutDataIfEmpty()
            repository.checkoutDataFlow.collect { list ->
                _productList.postValue(list)
            }
        }
    }

    fun toggleLike(name: String) {
        viewModelScope.launch {
            repository.toggleLike(name)
        }
    }
}