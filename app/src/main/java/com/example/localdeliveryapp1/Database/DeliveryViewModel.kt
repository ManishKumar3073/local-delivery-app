import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localdeliveryapp1.Database.DeliveryEntity
import com.example.localdeliveryapp1.Database.DeliveryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class CartItem(
    val productName: String,
    val price: Double,
    val quantity: Int
)
class DeliveryViewModel(private val repository: DeliveryRepository) : ViewModel() {

    private val _allDeliveries = MutableStateFlow<List<DeliveryEntity>>(emptyList())
    val allDeliveries: StateFlow<List<DeliveryEntity>> = _allDeliveries

    var customerName = ""
    var customerPhone = ""
    var customerAddress = ""

    private val _cart = MutableStateFlow<List<CartItem>>(emptyList())
    val cart: StateFlow<List<CartItem>> = _cart


    init {
        viewModelScope.launch {
            repository.getAllDeliveries().collectLatest { list ->
                _allDeliveries.value = list
            }
        }
    }

    fun saveCustomerDetails(
        name: String,
        phone: String,
        address: String
    ) {
        customerName = name
        customerPhone = phone
        customerAddress = address
    }
    fun addToCart(productName: String, price: Double) {

        val currentCart = _cart.value.toMutableList()

        val existingItem = currentCart.find {
            it.productName == productName
        }

        if (existingItem == null) {

            currentCart.add(
                CartItem(
                    productName = productName,
                    price = price,
                    quantity = 1
                )
            )

        } else {

            val index = currentCart.indexOf(existingItem)

            currentCart[index] = existingItem.copy(
                quantity = existingItem.quantity + 1
            )
        }

        _cart.value = currentCart
    }
    fun removeFromCart(productName: String) {

        val currentCart = _cart.value.toMutableList()

        val existingItem = currentCart.find {
            it.productName == productName
        }

        if (existingItem != null) {

            if (existingItem.quantity == 1) {

                currentCart.remove(existingItem)

            } else {

                val index = currentCart.indexOf(existingItem)

                currentCart[index] = existingItem.copy(
                    quantity = existingItem.quantity - 1
                )
            }
        }

        _cart.value = currentCart
    }
    fun getCartTotal(): Double {

        return _cart.value.sumOf {
            it.price * it.quantity
        }
    }
    fun clearCart() {
        _cart.value = emptyList()
    }

    fun placeOrder(shopName: String) {

        val items = _cart.value

        if (items.isEmpty()) return

        val productSummary = items.joinToString(", ") {
            "${it.productName} x${it.quantity}"
        }

        val order = DeliveryEntity(
            customerName = customerName,
            customerPhone = customerPhone,
            address = customerAddress,
            orderDate = "2026-10-03",
            deliveryCharge = 20.0,
            totalAmount = getCartTotal(),
            status = "Pending",
            paymentMode = "Cash",
            productName = productSummary,
            shopName = shopName
        )

        insert(order)

        // Order is now saved, so empty the cart
        clearCart()
    }
    fun insert(delivery: DeliveryEntity) {
        viewModelScope.launch {
            repository.insert(delivery)
        }
    }

    fun delete(delivery: DeliveryEntity) {
        viewModelScope.launch {
            repository.delete(delivery)
        }
    }

    fun update(delivery: DeliveryEntity) {
        viewModelScope.launch {
            repository.update(delivery)
        }
    }
}
