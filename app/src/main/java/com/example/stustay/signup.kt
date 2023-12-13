package com.example.stustay

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.util.Log
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.stustay.Model.User
import com.example.stustay.Repository.RestApi
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    private lateinit var restApi: RestApi
    private lateinit var rolesSpinner: Spinner

    private lateinit var nameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var genderEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        restApi = RestApi.create()

        // Initialize your views
        nameEditText = findViewById(R.id.nameEditText)
        lastNameEditText = findViewById(R.id.lastNameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        ageEditText = findViewById(R.id.ageEditText)
        genderEditText = findViewById(R.id.genderEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        signUpButton = findViewById(R.id.signUpButton)
        rolesSpinner = findViewById(R.id.rolesSpinner) // Add this line to initialize rolesSpinner

        // Populate the Spinner with roles
        val roles = arrayOf("admin", "proprietaire", "etudiant")
        val rolesAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        rolesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        rolesSpinner.adapter = rolesAdapter

        // Set click listener for sign-up button
        signUpButton.setOnClickListener {
            // Perform input validation
            if (isInputValid()) {
                // Input is valid, proceed with sign-up logic
                // You can access the input values using the respective EditText objects
                val name = nameEditText.text.toString()
                val lastName = lastNameEditText.text.toString()
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                val age = ageEditText.text.toString().toInt()
                val gender = genderEditText.text.toString()
                val phone = phoneEditText.text.toString()
                val selectedRole = rolesSpinner.selectedItem.toString()
                register(name,lastName,email,password,age,gender,phone,selectedRole, context = this)
            }
        }
    }
    private fun register(name: String , lastName : String , email: String,password: String,age : Int,gender: String,phone : String,selectedRole: String,context: Context) {
        Log.e("bbb", "bbb")
        val info = RestApi.RegisterRequest(
            name,lastName,email,password,age,gender,phone,selectedRole
        )
        val call = restApi.register(info)
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
    private fun isInputValid(): Boolean {
        // Perform validation for each input field
        if (nameEditText.text.isNullOrBlank() ||
            lastNameEditText.text.isNullOrBlank() ||
            !Patterns.EMAIL_ADDRESS.matcher(emailEditText.text.toString()).matches() ||
            passwordEditText.text.length < 8 ||
            ageEditText.text.isNullOrBlank() ||
            genderEditText.text.isNullOrBlank() ||
            phoneEditText.text.isNullOrBlank()
        ) {
            // Show an error message for invalid input
            Toast.makeText(this, "Please enter valid information", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
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
