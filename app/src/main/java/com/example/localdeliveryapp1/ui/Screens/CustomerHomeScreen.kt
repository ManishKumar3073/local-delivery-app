package com.example.localdeliveryapp1.Screens

import DeliveryViewModel
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerHomeScreen(navController: NavController, vm: DeliveryViewModel) {
    val shops = listOf("Fresh Fruits Shop", "Daily Needs Store", "Bakery Corner", "Organic Mart")
//    val deliveries by vm.allDeliveries.collectAsState(initial = emptyList())

    Scaffold(
        topBar = { TopAppBar(title = { Text("Customer Home") }) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(shops) { shop ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("shop_products/$shop") },
                    elevation = CardDefaults.cardElevation(5.dp)
                ) {
                    Text(text = shop, modifier = Modifier.padding(16.dp))
                }
            }

//            items(deliveries) { delivery ->
//                DeliveryCard(
//                    delivery = delivery,
//                    onDelete = { vm.delete(it) },
//                    onMarkDelivered = { updated ->
//                        val u = updated.copy(status = "Delivered")
//                        vm.update(u)
//                    }
//                )
//            }
        }
    }
}
