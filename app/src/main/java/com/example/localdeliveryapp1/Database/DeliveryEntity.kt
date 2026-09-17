package com.example.localdeliveryapp1.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "delivery_table")
data class DeliveryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,                   // Unique order ID
    val customerName: String,          // Name of the customer
    val customerPhone: String,         // Contact number
    val address: String,               // Delivery address
    val orderDate: String,             // e.g. "2025-10-05"
    val deliveryCharge: Double,        // Delivery fee
    val totalAmount: Double,           // Total price of the order
    val status: String,                // e.g. "Pending", "Delivered", "Cancelled"
    val paymentMode: String,            // e.g. "Cash", "UPI", "Card"
    val productName: String
)
