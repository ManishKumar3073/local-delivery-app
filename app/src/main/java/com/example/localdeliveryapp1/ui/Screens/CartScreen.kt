package com.example.localdeliveryapp1.Screens

import DeliveryViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.localdeliveryapp1.Database.DeliveryEntity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController, vm: DeliveryViewModel) {
    val deliveries by vm.allDeliveries.collectAsState(initial = emptyList())

    Scaffold(
        topBar = { TopAppBar(title = { Text("Cart") }) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(deliveries) { delivery ->
                DeliveryCard(delivery = delivery, onDelete = { vm.delete(it) }, onMarkDelivered = {
                    val updated = it.copy(status = "Delivered")
                    vm.update(updated)
                })
            }
        }
    }
}

@Composable
fun DeliveryCard(
    delivery: DeliveryEntity,
    onDelete: (DeliveryEntity) -> Unit,
    onMarkDelivered: (DeliveryEntity) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Customer: ${delivery.customerName}")
            Text("Address: ${delivery.address}")
            Text("Status: ${delivery.status}")
            Text("Product: ${delivery.productName}")
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { onMarkDelivered(delivery) }, modifier = Modifier.weight(1f)) {
                    Text("Mark Delivered")
                }
                Button(onClick = { onDelete(delivery) }, modifier = Modifier.weight(1f)) {
                    Text("Delete")
                }
            }
        }
    }
}
