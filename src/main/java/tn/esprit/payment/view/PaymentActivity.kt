package tn.esprit.payment.view
// import android.os.Bundle
// import androidx.appcompat.app.AppCompatActivity
// import androidx.lifecycle.ViewModelProvider
// import tn.esprit.payment.R
// import tn.esprit.payment.repository.PaymentRepository
// import tn.esprit.payment.viewmodelfactory.PaymentViewModelFactory
// import tn.esprit.payment.viewmodels.PaymentViewModel
//
// class PaymentActivity : AppCompatActivity() {
//
// private lateinit var viewModel: PaymentViewModel
//
// override fun onCreate(savedInstanceState: Bundle?) {
// super.onCreate(savedInstanceState)
// setContentView(R.layout.paymentmethod)
//
// val repository = PaymentRepository()
// viewModel = ViewModelProvider(this, PaymentViewModelFactory(repository))
// .get(PaymentViewModel::class.java)
//
// confirmbutton.setOnClickListener {
// val logementId = "your_logement_id"
// val paymentData = PaymentData(
// amount = 100.0,
// date = "2023-12-01",
// method = "Credit Card",
// numberOfRoommates = 3,
// isRecurringPayment = false,
// recurringPaymentFrequency = "",
// paymentType = listOf("water", "light")
// )
//
// viewModel.postPayment(logementId, paymentData)
// }
//