package com.example.stustay.Ui.Activities
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.stustay.R
import com.example.stustay.Ui.Activties.PostActivity

class LauchScreenActivity:AppCompatActivity() {
    private val delay : Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_screen)


        Handler().postDelayed({
            val   intent = Intent(this ,HomeActivity::class.java)
            startActivity(intent)
            finish()
        },delay)
    }
}