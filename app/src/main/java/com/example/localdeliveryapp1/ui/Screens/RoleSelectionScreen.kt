package com.example.localdeliveryapp1.ui.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.localdeliveryapp1.Navigation.Screen

@Composable
fun RoleSelectionScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(text = "Welcome! Select Your Role")

            Button(onClick = { navController.navigate(Screen.ShopkeeperDetails.route) }) {
                Text(text = "I am a Shopkeeper")
            }

            Button(onClick = { navController.navigate(Screen.CustomerDetails.route) }) {
                Text(text = "I am a Customer")
            }
        }
    }
}
