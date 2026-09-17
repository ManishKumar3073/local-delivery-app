package com.example.localdeliveryapp1.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localdeliveryapp1.Database.DeliveryEntity
import com.example.localdeliveryapp1.Database.DeliveryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest

class ShopkeeperViewModel(private val repository: DeliveryRepository) : ViewModel() {

    private val _deliveries = MutableStateFlow<List<DeliveryEntity>>(emptyList())
    val deliveries: StateFlow<List<DeliveryEntity>> = _deliveries

    init {
        // Collect Flow only once
        viewModelScope.launch {
            repository.getAllDeliveries().collectLatest { list ->
                _deliveries.value = list
            }
        }
    }

    fun addDelivery(delivery: DeliveryEntity) {
        viewModelScope.launch {
            repository.insert(delivery)
        }
    }

    fun updateDelivery(delivery: DeliveryEntity) {
        viewModelScope.launch {
            repository.update(delivery)
        }
    }

    fun deleteDelivery(delivery: DeliveryEntity) {
        viewModelScope.launch {
            repository.delete(delivery)
        }
    }
}
