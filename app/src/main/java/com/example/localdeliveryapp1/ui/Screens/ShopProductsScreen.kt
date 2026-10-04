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

data class Product(
    val name: String,
    val price: Double
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopProductsScreen(
    navController: NavController,
    shopName: String,
    vm: DeliveryViewModel
) {

    // Keep all your different shops and their products
    val products = when (shopName) {

        "Fresh Fruits Shop" -> listOf(
            Product("Apples", 80.0),
            Product("Bananas", 50.0),
            Product("Oranges", 70.0),
            Product("Mangoes", 100.0)
        )

        "Daily Needs Store" -> listOf(
            Product("Milk", 60.0),
            Product("Bread", 40.0),
            Product("Rice", 80.0),
            Product("Sugar", 50.0)
        )

        "Bakery Corner" -> listOf(
            Product("Bread", 40.0),
            Product("Cake", 300.0),
            Product("Cookies", 120.0),
            Product("Bun", 30.0)
        )

        "Organic Mart" -> listOf(
            Product("Organic Rice", 150.0),
            Product("Organic Milk", 80.0),
            Product("Honey", 250.0),
            Product("Almonds", 500.0)
        )

        else -> emptyList()
    }

    val cart by vm.cart.collectAsState()

    var showCartDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text(shopName)
                },

                actions = {

                    // CART BUTTON
                    TextButton(
                        onClick = {
                            showCartDialog = true
                        }
                    ) {

                        Text("Cart")
                    }
                }
            )
        }

    ) { paddingValues ->

        LazyColumn(

            verticalArrangement = Arrangement.spacedBy(12.dp),

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)

        ) {

            items(products) { product ->

                val cartItem = cart.find {
                    it.productName == product.name
                }

                val quantity = cartItem?.quantity ?: 0

                Card(

                    modifier = Modifier.fillMaxWidth(),

                    elevation = CardDefaults.cardElevation(
                        5.dp
                    )

                ) {

                    Row(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),

                        horizontalArrangement =
                            Arrangement.SpaceBetween,

                        verticalAlignment =
                            Alignment.CenterVertically

                    ) {

                        // PRODUCT INFORMATION
                        Column {

                            Text(
                                text = product.name
                            )

                            Text(
                                text = "₹${product.price}"
                            )
                        }

                        // IF NOT IN CART
                        if (quantity == 0) {

                            Button(

                                onClick = {

                                    vm.addToCart(
                                        product.name,
                                        product.price
                                    )
                                }

                            ) {

                                Text("Add")
                            }

                        }

                        // IF ALREADY IN CART
                        else {

                            Row(

                                verticalAlignment =
                                    Alignment.CenterVertically

                            ) {

                                Button(

                                    onClick = {

                                        vm.removeFromCart(
                                            product.name
                                        )
                                    }

                                ) {

                                    Text("-")
                                }

                                Text(

                                    text = quantity.toString(),

                                    modifier = Modifier.padding(
                                        horizontal = 12.dp
                                    )
                                )

                                Button(

                                    onClick = {

                                        vm.addToCart(
                                            product.name,
                                            product.price
                                        )
                                    }

                                ) {

                                    Text("+")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showCartDialog) {

        AlertDialog(

            onDismissRequest = {
                showCartDialog = false
            },

            title = {
                Text("Your Cart")
            },

            text = {

                if (cart.isEmpty()) {

                    Text("Your cart is empty")

                } else {

                    Column(
                        verticalArrangement =
                            Arrangement.spacedBy(8.dp)
                    ) {

                        cart.forEach { item ->

                            Text(
                                "${item.productName} × ${item.quantity} = ₹${item.price * item.quantity}"
                            )
                        }

                        HorizontalDivider()

                        Text(
                            "Total: ₹${vm.getCartTotal()}",
                            style =
                                MaterialTheme.typography.titleMedium
                        )
                    }
                }
            },

            confirmButton = {

                Button(

                    enabled = cart.isNotEmpty(),

                    onClick = {
                        vm.placeOrder(shopName)
                        showCartDialog = false
                    }

                ) {

                    Text("Place Order")
                }
            },

            dismissButton = {

                TextButton(

                    onClick = {
                        showCartDialog = false
                    }

                ) {

                    Text("Close")
                }
            }
        )
    }
}



//package com.example.localdeliveryapp1.Screens
//
//import DeliveryViewModel
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//
//data class Product(
//    val name: String,
//    val price: Double
//)
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ShopProductsScreen(
//    navController: NavController,
//    shopName: String,
//    vm: DeliveryViewModel
//) {
//
//    // Products according to shop
//    val products = when (shopName) {
//
//        "Fresh Fruits Shop" -> listOf(
//            Product("Apples", 80.0),
//            Product("Bananas", 50.0),
//            Product("Oranges", 70.0),
//            Product("Mangoes", 100.0)
//        )
//
//        "Daily Needs Store" -> listOf(
//            Product("Milk", 60.0),
//            Product("Bread", 40.0),
//            Product("Rice", 80.0),
//            Product("Sugar", 50.0)
//        )
//
//        "Bakery Corner" -> listOf(
//            Product("Bread", 40.0),
//            Product("Cake", 300.0),
//            Product("Cookies", 120.0),
//            Product("Bun", 30.0)
//        )
//
//        "Organic Mart" -> listOf(
//            Product("Organic Rice", 150.0),
//            Product("Organic Milk", 80.0),
//            Product("Honey", 250.0),
//            Product("Almonds", 500.0)
//        )
//
//        else -> emptyList()
//    }
//
//    // Stores quantity of each product
////    val cart = remember {
////        mutableStateMapOf<String, Int>()
////    }
//    val cart by vm.cart.collectAsState()
//    val total=vm.getCartTotal()
//
//    // Controls cart dialog
//    var showCartDialog by remember {
//        mutableStateOf(false)
//    }
//
//    // Calculate total
//    val totalPrice = products.sumOf { product ->
//        product.price * (cart[product.name] ?: 0)
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(shopName)
//                },
//                actions = {
//
//                    // View Cart button
//                    TextButton(
//                        onClick = {
//                            showCartDialog = true
//                        }
//                    ) {
//                        Text("Cart")
//                    }
//                }
//            )
//        }
//    ) { paddingValues ->
//
//        LazyColumn(
//            verticalArrangement = Arrangement.spacedBy(12.dp),
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//                .padding(16.dp)
//        ) {
//
//            items(products) { product ->
//
//                val quantity = cart[product.name] ?: 0
//
//                Card(
//                    modifier = Modifier.fillMaxWidth(),
//                    elevation = CardDefaults.cardElevation(5.dp)
//                ) {
//
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(16.dp),
//
//                        horizontalArrangement = Arrangement.SpaceBetween,
//
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//
//                        // Product name + price
//                        Column {
//
//                            Text(
//                                text = product.name,
//                                style = MaterialTheme.typography.bodyLarge
//                            )
//
//                            Text(
//                                text = "₹${product.price}",
//                                style = MaterialTheme.typography.bodyMedium
//                            )
//                        }
//
//                        // If product is not in cart
//                        if (quantity == 0) {
//
//                            Button(
//                                onClick = {
//                                    cart[product.name] = 1
//                                }
//                            ) {
//                                Text("Add to Cart")
//                            }
//
//                        } else {
//
//                            // If product is already in cart
//                            Row(
//                                verticalAlignment = Alignment.CenterVertically,
//                                horizontalArrangement = Arrangement.spacedBy(8.dp)
//                            ) {
//
//                                Button(
//                                    onClick = {
//
//                                        if (quantity == 1) {
//                                            cart.remove(product.name)
//                                        } else {
//                                            cart[product.name] = quantity - 1
//                                        }
//
//                                    }
//                                ) {
//                                    Text("-")
//                                }
//
//                                Text(
//                                    text = quantity.toString(),
//                                    style = MaterialTheme.typography.titleMedium
//                                )
//
//                                Button(
//                                    onClick = {
//                                        cart[product.name] = quantity + 1
//                                    }
//                                ) {
//                                    Text("+")
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    // Cart dialog
//    if (showCartDialog) {
//
//        AlertDialog(
//
//            onDismissRequest = {
//                showCartDialog = false
//            },
//
//            title = {
//                Text("Your Cart")
//            },
//
//            text = {
//
//                Column(
//                    verticalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//
//                    val selectedProducts = products.filter {
//                        (cart[it.name] ?: 0) > 0
//                    }
//
//                    if (selectedProducts.isEmpty()) {
//
//                        Text("Your cart is empty")
//
//                    } else {
//
//                        selectedProducts.forEach { product ->
//
//                            val quantity = cart[product.name] ?: 0
//
//                            Text(
//                                "${product.name} × $quantity = ₹${product.price * quantity}"
//                            )
//                        }
//
//                        HorizontalDivider()
//
//                        Text(
//                            "Total: ₹$totalPrice",
//                            style = MaterialTheme.typography.titleMedium
//                        )
//                    }
//                }
//            },
//
//            confirmButton = {
//
//                Button(
//
//                    enabled = cart.isNotEmpty(),
//
//                    onClick = {
//
//                        // We will connect this to Room in Step 2
//
//                        showCartDialog = false
//                    }
//
//                ) {
//                    Text("Place Order")
//                }
//            },
//
//            dismissButton = {
//
//                TextButton(
//                    onClick = {
//                        showCartDialog = false
//                    }
//                ) {
//                    Text("Close")
//                }
//            }
//        )
//    }
//}
//
//
//
