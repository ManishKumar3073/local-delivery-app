package com.example.localdeliveryapp1.Database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DeliveryDao {


    @Query("SELECT * FROM delivery_table ORDER BY id DESC")
    fun getAllDeliveries(): Flow<List<DeliveryEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: DeliveryEntity)


    @Delete
    suspend fun deleteOrder(order: DeliveryEntity)


    @Update
    suspend fun updateDelivery(delivery: DeliveryEntity)


    @Query("SELECT * FROM delivery_table WHERE status = :status ORDER BY id DESC")
    fun getDeliveriesByStatus(status: String): Flow<List<DeliveryEntity>>
}
