import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stustay.Model.Logement
import com.example.stustay.Model.LogementDetails
import com.example.stustay.Repository.LogementRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class LogementViewModel(private val repository: LogementRepository) : ViewModel() {

    private val _logements = MutableLiveData<List<Logement>>()
    val logements: LiveData<List<Logement>> get() = _logements

    private val _logementDetails = MutableLiveData<LogementDetails>()
    val logementDetails: LiveData<LogementDetails> get() = _logementDetails

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage


    fun getAllLogements() {
        viewModelScope.launch {
            try {
                val response: Response<List<Logement>> = repository.getAllLogements()
                if (response.isSuccessful) {
                    _logements.value = response.body()
                } else {
                    _errorMessage.value = "Erreur lors de la récupération des logements"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erreur réseau"
            }
        }
    }


    fun getLogementDetails(id: String) {
        viewModelScope.launch {
            try {
                val response: Response<LogementDetails> = repository.getLogementDetails(id)
                if (response.isSuccessful) {
                    _logementDetails.value = response.body()
                } else {
                    _errorMessage.value = "Erreur lors de la récupération des détails du logement"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erreur réseau"
            }
        }
    }


}
