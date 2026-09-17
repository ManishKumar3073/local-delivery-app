package com.example.localdeliveryapp1

import DeliveryViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.localdeliveryapp1.Navigation.Screen

import com.example.localdeliveryapp1.Database.DeliveryDatabase
import com.example.localdeliveryapp1.Database.DeliveryRepository
import com.example.localdeliveryapp1.Database.DeliveryViewModelFactory


import com.example.localdeliveryapp1.ViewModel.ShopkeeperViewModel
import com.example.localdeliveryapp1.ViewModel.ShopkeeperViewModelFactory

// Screens
import com.example.localdeliveryapp1.ui.Screens.RoleSelectionScreen
import com.example.localdeliveryapp1.ui.Screens.ShopkeeperDetailsScreen
import com.example.localdeliveryapp1.ui.Screens.CustomerDetailsScreen
import com.example.localdeliveryapp1.ui.Screens.AddProductScreen

import com.example.localdeliveryapp1.Screens.ShopkeeperHomeScreen
import com.example.localdeliveryapp1.Screens.CustomerHomeScreen
import com.example.localdeliveryapp1.Screens.ShopProductsScreen
import com.example.localdeliveryapp1.Screens.CartScreen
import com.example.localdeliveryapp1.Screens.DatabaseTestScreen

import com.example.localdeliveryapp1.ui.theme.LocalDeliveryApp1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LocalDeliveryApp1Theme {

                val context = LocalContext.current
                val navController = rememberNavController()


                val db = remember { DeliveryDatabase.getDatabase(context) }
                val repo = remember { DeliveryRepository(db.deliveryDao()) }


                val deliveryVM: DeliveryViewModel =
                    viewModel(factory = DeliveryViewModelFactory(repo))

                val shopkeeperVM: ShopkeeperViewModel =
                    viewModel(factory = ShopkeeperViewModelFactory(repo))

                NavHost(
                    navController = navController,
                    startDestination = Screen.RoleSelection.route
                ) {

                    composable(Screen.RoleSelection.route) {
                        RoleSelectionScreen(navController)
                    }

                    composable(Screen.ShopkeeperDetails.route) {
                        ShopkeeperDetailsScreen(navController)
                    }

                    composable(Screen.CustomerDetails.route) {
                        CustomerDetailsScreen(navController)
                    }

                    composable(Screen.ShopkeeperHome.route) {
                        ShopkeeperHomeScreen(navController, shopkeeperVM)
                    }

                    composable(Screen.CustomerHome.route) {
                        CustomerHomeScreen(navController, deliveryVM)
                    }

                    composable(Screen.AddProduct.route) {
                        AddProductScreen(navController)
                    }

                    composable(Screen.Cart.route) {
                        CartScreen(navController, deliveryVM)
                    }

                    composable("shop_products/{shopName}") { backStackEntry ->
                        val shopName = backStackEntry.arguments?.getString("shopName") ?: "Shop"
                        ShopProductsScreen(navController, shopName, deliveryVM)
                    }

                    composable("DatabaseTestScreen") {
                        DatabaseTestScreen(vm = deliveryVM)
                    }
                }
            }
        }
    }
}
