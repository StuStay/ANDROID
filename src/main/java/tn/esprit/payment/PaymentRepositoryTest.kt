package tn.esprit.payment
import com.google.gson.JsonObject
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import tn.esprit.payment.models.Payment
import tn.esprit.payment.repository.PaymentRepository
import tn.esprit.payment.services.Paymentservice

class PaymentRepositoryTest {

    private lateinit var mockService: Paymentservice
    private var capturedData: JsonObject? = null
    private lateinit var repository: PaymentRepository

    @Before
    fun setup() {
        mockService = object : Paymentservice {
            override suspend fun postPayment(jsonData: JsonObject) {
                capturedData = jsonData
            }

            override suspend fun deletePayment(paymentId: String) {
            }
        }
        repository = PaymentRepository(mockService)
    }

    @Test
    fun testPostPayment() {
        val paymentData = Payment(
            amount = 900,
            date = "2023-12-01",
            method = "Credit Card",
            numberOfRoommates = 4,
            isRecurringPayment = false,
            recurringPaymentFrequency = "Monthly",
            paymentType = listOf("water", "light")
        )

        runBlocking {
            repository.postPayment(paymentData)
        }

    }
}


