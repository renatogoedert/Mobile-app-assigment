package org.wit.placemark.views.login


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import org.wit.placemark.databinding.ActivityLoginBinding
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.wit.placemark.main.MainApp
import timber.log.Timber.Forest.i
import org.wit.placemark.views.placemarklist.PlacemarkListView
import org.wit.placemark.models.UserModel
import org.wit.placemark.views.singin.SingInView



class LoginView : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    lateinit var app: MainApp

    lateinit var username : EditText
    lateinit var password : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp
        i("Login Activity started...")


        binding.loginBtn.setOnClickListener {
            val enteredUsername = binding.username.text.toString()
            val enteredPassword = binding.password.text.toString()

            val user = app.users.authenticate(enteredUsername, enteredPassword)


            if (user != false) {
                Toast.makeText(this, "Login successfully", Toast.LENGTH_LONG).show()
                i("Login successfully")
                val intent = Intent(this, PlacemarkListView::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_LONG).show()
            }
        }

        binding.signupBtn.setOnClickListener {
            val intent = Intent(this, SingInView::class.java)
            startActivity(intent)
        }

    }
}