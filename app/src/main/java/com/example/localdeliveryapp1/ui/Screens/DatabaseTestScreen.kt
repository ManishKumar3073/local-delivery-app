package com.example.localdeliveryapp1.Screens

import DeliveryViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.localdeliveryapp1.Database.DeliveryEntity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatabaseTestScreen(vm: DeliveryViewModel) {
    val scope = rememberCoroutineScope()

    var customerName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    val orders by vm.allDeliveries.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Room Database Test") })
        }
    ) { padding ->
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = customerName,
                onValueChange = { customerName = it },
                label = { Text("Customer Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )

            Button(
                onClick = {
                    val order = DeliveryEntity(
                        customerName = customerName,
                        customerPhone = "9999999999",
                        address = address,
                        orderDate = "2025-10-05",
                        deliveryCharge = 50.0,
                        totalAmount = 200.0,
                        status = "Pending",
                        paymentMode = "Cash",
                        productName = "product"
                    )

                    scope.launch {
                        vm.insert(order)
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Add Delivery")
            }

            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                items(orders) { o ->
                    Text("${o.id}. ${o.customerName} - ${o.address}")
                }
            }
        }
    }
}
