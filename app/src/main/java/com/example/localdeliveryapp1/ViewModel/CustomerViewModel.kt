package com.example.localdeliveryapp1.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localdeliveryapp1.Database.DeliveryEntity
import com.example.localdeliveryapp1.Database.DeliveryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CustomerViewModel(private val repository: DeliveryRepository) : ViewModel() {

    val allDeliveries: Flow<List<DeliveryEntity>> = repository.getAllDeliveries()

    fun insertOrder(order: DeliveryEntity) {
        viewModelScope.launch {
            repository.insert(order)
        }
    }

    fun deleteOrder(order: DeliveryEntity) {
        viewModelScope.launch {
            repository.delete(order)
        }
    }
}
