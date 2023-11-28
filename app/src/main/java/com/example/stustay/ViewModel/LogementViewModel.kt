import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stustay.Model.Logement
import com.example.stustay.Repository.LogementRepository
import com.example.stustay.Retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class LogementViewModel(private val logementRepository: LogementRepository) : ViewModel() {

    private val _logements = MutableLiveData<List<Logement>>()
    val logements: LiveData<List<Logement>> get() = _logements

    init {
        viewModelScope.launch {
            _logements.value = logementRepository.getAllLogements()
        }
    }
}

