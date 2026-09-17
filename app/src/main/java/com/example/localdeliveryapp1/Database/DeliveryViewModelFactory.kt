package com.example.localdeliveryapp1.Database

import DeliveryViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DeliveryViewModelFactory(private val repository: DeliveryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeliveryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DeliveryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
