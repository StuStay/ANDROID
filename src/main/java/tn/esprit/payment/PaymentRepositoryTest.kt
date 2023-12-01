package tn.esprit.payment
import com.google.gson.JsonObject
import junit.framework.TestCase.assertNotNull
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
                println("Delete succeeded! Payment ID: $paymentId")
            }

            override suspend fun getAllPayments(): List<Payment> {
                return emptyList()
            }
        }
        repository = PaymentRepository(mockService)
    }

    @Test
    fun testPostPayment() {
        val paymentData = Payment(
            amount = 3,
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

        if (capturedData != null) {
            println("Post succeeded! Captured data: $capturedData")
        } else {
            println("Post failed!")
        }
    }

    @Test
    fun testGetAllPayments() {
        runBlocking {
            val payments = repository.getAllPayments()
            println("Received payments: $payments")
            assertNotNull(payments)
        }
    }

    @Test
    fun testDeletePayment() {
        val paymentIdToDelete = "6568079ff99d2638d97fa0f7"

        runBlocking {
            repository.deletePayment(paymentIdToDelete)
        }

    }
}
