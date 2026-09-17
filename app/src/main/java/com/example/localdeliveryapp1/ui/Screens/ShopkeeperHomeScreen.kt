package com.example.localdeliveryapp1.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.localdeliveryapp1.Database.DeliveryEntity
import com.example.localdeliveryapp1.ViewModel.ShopkeeperViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopkeeperHomeScreen(navController: NavController, vm: ShopkeeperViewModel) {

    val deliveries by vm.deliveries.collectAsState(initial = emptyList())

//    var customerName by remember { mutableStateOf("") }
//    var customerPhone by remember { mutableStateOf("") }
//    var address by remember { mutableStateOf("") }
//    var totalAmount by remember { mutableStateOf("") }
//    var productName by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Shopkeeper Home") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(2.dp)) {

            Column(modifier = Modifier.padding(0.dp)) {

//                OutlinedTextField(value = customerName, onValueChange = { customerName = it }, label = { Text("Customer Name") }, modifier = Modifier.fillMaxWidth())
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(value = customerPhone, onValueChange = { customerPhone = it }, label = { Text("Customer Phone") }, modifier = Modifier.fillMaxWidth())
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Address") }, modifier = Modifier.fillMaxWidth())
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(value = totalAmount, onValueChange = { totalAmount = it }, label = { Text("Total Amount") }, modifier = Modifier.fillMaxWidth())
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(value = productName, onValueChange = { productName = it }, label = { Text("Product Name") }, modifier = Modifier.fillMaxWidth())
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Button(onClick = {
//                    if (customerName.isNotBlank() && address.isNotBlank() && totalAmount.isNotBlank()) {
//                        val order = DeliveryEntity(
//                            customerName = customerName,
//                            customerPhone = customerPhone,
//                            address = address,
//                            orderDate = "2025-10-05",
//                            deliveryCharge = 50.0,
//                            totalAmount = totalAmount.toDouble(),
//                            status = "Pending",
//                            paymentMode = "Cash",
//                            productName = productName
//                        )
//                        vm.addDelivery(order)
//
//                        customerName = ""
//                        customerPhone = ""
//                        address = ""
//                        totalAmount = ""
//                        productName = ""
//                    }
//                }) {
//                    Text("Add Delivery")
//                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("All Deliveries:", style = MaterialTheme.typography.titleMedium)

                LazyColumn(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                    items(deliveries) { delivery ->
                        DeliveryItem(delivery = delivery, vm = vm)
                    }
                }
            }
        }
    }
}

@Composable
fun DeliveryItem(delivery: DeliveryEntity, vm: ShopkeeperViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("Customer: ${delivery.customerName}")
            Text("Phone: ${delivery.customerPhone}")
            Text("Address: ${delivery.address}")
            Text("Status: ${delivery.status}")
            Text("Total: ₹${delivery.totalAmount}")

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Button(onClick = {
                    val updatedDelivery = delivery.copy(status = "Delivered")
                    vm.updateDelivery(updatedDelivery)
                }, modifier = Modifier.weight(1f)) {
                    Text("Mark Delivered")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = {
                    vm.deleteDelivery(delivery)
                }, modifier = Modifier.weight(1f)) {
                    Text("Delete")
                }
            }
        }
    }
}
