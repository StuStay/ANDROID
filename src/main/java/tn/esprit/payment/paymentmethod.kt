package tn.esprit.payment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.payment.databinding.PaymentmethodBinding

class PaymentMethodActivity : AppCompatActivity() {
    private lateinit var binding: PaymentmethodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PaymentmethodBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val logementId = "6563f4b90822ca03ec0bb5a0"
        val logementInfo = getLogementInformation(logementId)

        if (logementInfo != null) {
            val (prix, nombreChambre) = logementInfo


            binding.amountoutput.text = "$prix"
            binding.roomsoutput.text = "$nombreChambre"
        } else {
            println("Error fetching logement information.")
        }
    }
}

