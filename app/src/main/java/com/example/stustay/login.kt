package com.example.stustay
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.stustay.Model.User
import com.example.stustay.Repository.RestApi
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class login : AppCompatActivity() {
    private lateinit var restApi: RestApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        restApi = RestApi.create()

        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val signInButton: Button = findViewById(R.id.signInButton)
        val registerButton: TextView = findViewById(R.id.signUpTextView)
        registerButton.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            this.startActivity(intent)
        }
        signInButton.setOnClickListener {
            // Retrieve email and password
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Perform validation checks
            if (isEmailValid(email) && isPasswordValid(password)) {
                // Navigate to MainActivity
                login(email,password, context = this)
            } else {
                // Show toast message for validation failure
                if (!isEmailValid(email)) {
                    showToast("Invalid email address")
                } else if (!isPasswordValid(password)) {
                    showToast("Password must be at least 8 characters long")
                }
            }
        }
    }

    // Validation checks
    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun login(email: String,password: String,context: Context) {
        Log.e("bbb", "bbb")
        val info = RestApi.LoginRequest(
            email = email,
            password = password
        )
        val call = restApi.login(info)
        call.enqueue(object :
            Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response:
            Response<JsonObject>
            ) {
                if (response.isSuccessful) {
                    val users = response.body()
                    Log.e("ooo", "ooo")
                    Log.e("test", users.toString())
                    if (users != null) {
                        val gson = Gson()
                        val jsonSTRING = response.body()?.toString()
                        val jsonObject = gson.fromJson(jsonSTRING, JsonObject::class.java)

                        val userObject = jsonObject?.getAsJsonObject("user")
                        userObject?.let { saveUserInSharedPreferences(it,context) }
                        val intent = Intent(context, profile::class.java)
                        context.startActivity(intent)
                    }
                } else {
                    Log.e("hhh", "hhh")
                }
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("zzz", "zz")
            }
        })
    }
    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 8
    }

    // Show a toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun saveUserInSharedPreferences(user: JsonObject, context: Context) {
        val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Extract user information
        val userId = user.getAsJsonPrimitive("_id")?.asString
        val name = user.getAsJsonPrimitive("name")?.asString
        val lastName = user.getAsJsonPrimitive("last_name")?.asString
        val password = user.getAsJsonPrimitive("password")?.asString
        val email = user.getAsJsonPrimitive("email")?.asString
        val avatar = user.getAsJsonPrimitive("avatar")?.asString
        val age = user.getAsJsonPrimitive("age")?.asInt
        val gender = user.getAsJsonPrimitive("gender")?.asString
        val phone = user.getAsJsonPrimitive("phone")?.asString
        val role = user.getAsJsonPrimitive("role")?.asString

        // Save user information
        editor.putString("_id", userId)
        editor.putString("name", name)
        editor.putString("last_name", lastName)
        editor.putString("password", password)
        editor.putString("email", email)
        editor.putString("avatar", avatar)
        editor.putInt("age", age ?: 0) // If age is null, default to 0
        editor.putString("gender", gender)
        editor.putString("phone", phone)
        editor.putString("role", role)

        // Apply changes
        editor.apply()
    }

}
