package com.example.localdeliveryapp1.Database

class DeliveryRepository(private val dao: DeliveryDao) {

    fun getAllDeliveries() = dao.getAllDeliveries()

    suspend fun insert(order: DeliveryEntity) {
        dao.insertOrder(order)
    }

    suspend fun delete(order: DeliveryEntity) {
        dao.deleteOrder(order)
    }

    suspend fun update(order: DeliveryEntity) {
        dao.updateDelivery(order)
    }

    fun getDeliveriesByStatus(status: String) = dao.getDeliveriesByStatus(status)
}
