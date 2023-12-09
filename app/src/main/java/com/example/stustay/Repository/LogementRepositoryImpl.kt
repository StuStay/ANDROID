import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.stustay.Api.LogementApiService
import com.example.stustay.Model.Logement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.stustay.Repository.LogementRepository
import com.example.stustay.Repository.RetrofitInstance

class LogementRepositoryImpl(private val logementApiService: LogementApiService) :
    LogementRepository {
    override suspend fun createLogement(
        images: String?,
        titre: String,
        description: String,
        nom: String,
        nombreChambre: Int,
        prix: Double,
        contact: String,
        lieu: String
    ): MutableLiveData<Logement?> {
        return withContext(Dispatchers.IO) {
            val resultLiveData = MutableLiveData<Logement?>()

            try {
                val imagesJson = images?.let { "\"images\":\"$it\"," } ?: "\"images\":\"\","
                val requestBody = RequestBody.create(
                    "application/json".toMediaTypeOrNull(),
                    "{$imagesJson \"titre\":\"$titre\", \"description\":\"$description\", \"nom\":\"$nom\", \"nombreChambre\":\"$nombreChambre\", \"prix\":\"$prix\", \"contact\":\"$contact\", \"lieu\":\"$lieu\"}"
                )

                Log.d("LogementRepositoryImpl", "Request Body: ${requestBody.toString()}")

                val response = logementApiService.createLogement(requestBody).execute()

                Log.d("LogementRepositoryImpl", "Response Code: ${response.code()}")

                if (response.isSuccessful) {
                    val user = response.body()
                    resultLiveData.postValue(user)
                } else {
                    Log.e("LogementRepositoryImpl", "Error Response Body: ${response.errorBody()?.string()}")
                    resultLiveData.postValue(null)
                }
            } catch (e: Exception) {
                Log.e("LogementRepositoryImpl", "Exception: ${e.message}")
                resultLiveData.postValue(null)
            }

            resultLiveData
        }
    }

    override suspend fun getAllLogements(): LiveData<Logement?> {

        fun getTrajetSecurise() = liveData(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.getAllLogements()
                if (response != null) {
                    emit(response.body())
                }
            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "Exception: ${e.message}")
                emit(null)
            }
        }

        return getTrajetSecurise()
    }

}