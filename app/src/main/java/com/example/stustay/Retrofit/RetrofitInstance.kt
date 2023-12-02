import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.esprit.safeguardapplication.Api.LogementApiService

object RetrofitInstance {

    private const val BASE_URL = "http://localhost:3000"

    val logementApiService: LogementApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LogementApiService::class.java)
    }
}
