package com.example.localdeliveryapp1.Navigation

sealed class Screen(val route: String) {


    object RoleSelection : Screen("role_selection")


    object ShopkeeperDetails : Screen("shopkeeper_details")
    object CustomerDetails : Screen("customer_details")


    object ShopkeeperHome : Screen("shopkeeper_home")
    object CustomerHome : Screen("customer_home")


    object AddProduct : Screen("add_product")


    object Cart : Screen("cart")


    object ShopProducts : Screen("shop_products/{shopName}") {
        fun createRoute(shopName: String) = "shop_products/$shopName"
    }
}
