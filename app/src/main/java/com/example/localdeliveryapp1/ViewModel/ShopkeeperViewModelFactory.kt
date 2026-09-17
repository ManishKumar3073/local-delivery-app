package com.example.localdeliveryapp1.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.localdeliveryapp1.Database.DeliveryRepository

class ShopkeeperViewModelFactory(
    private val repository: DeliveryRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShopkeeperViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShopkeeperViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
