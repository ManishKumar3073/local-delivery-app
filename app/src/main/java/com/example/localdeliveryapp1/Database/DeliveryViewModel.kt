import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localdeliveryapp1.Database.DeliveryEntity
import com.example.localdeliveryapp1.Database.DeliveryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DeliveryViewModel(private val repository: DeliveryRepository) : ViewModel() {

    private val _allDeliveries = MutableStateFlow<List<DeliveryEntity>>(emptyList())
    val allDeliveries: StateFlow<List<DeliveryEntity>> = _allDeliveries

    init {
        viewModelScope.launch {
            repository.getAllDeliveries().collectLatest { list ->
                _allDeliveries.value = list
            }
        }
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
