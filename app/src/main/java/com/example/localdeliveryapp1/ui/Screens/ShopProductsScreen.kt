package com.example.localdeliveryapp1.Screens

import DeliveryViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.localdeliveryapp1.Database.DeliveryEntity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopProductsScreen(navController: NavController, shopName: String, vm: DeliveryViewModel) {

    val products = listOf("Apples", "Bananas", "Oranges", "Milk", "Bread")

    Scaffold(
        topBar = { TopAppBar(title = { Text(shopName) }) }
    ) { paddingValues ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(products) { product ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(5.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = product)
                        Button(onClick = {
                            val newDelivery = DeliveryEntity(
                                customerName = "Manish",
                                customerPhone = "1234567890",
                                address = "123 Street",
                                orderDate = "2025-10-06",
                                deliveryCharge = 20.0,
                                totalAmount = 100.0,
                                status = "Pending",
                                paymentMode = "Cash",
                                productName = product
                            )
                            vm.insert(newDelivery)
                        }) {
                            Text("Add to Cart")
                        }
                    }
                }
            }
        }
    }
}
