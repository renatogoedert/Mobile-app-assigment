package org.wit.placemark.views.singin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.wit.placemark.R

import org.wit.placemark.databinding.ActivitySingInBinding
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.UserModel
import org.wit.placemark.views.login.LoginView
import timber.log.Timber

class SingInView : AppCompatActivity() {

    private lateinit var binding: ActivitySingInBinding

    lateinit var app: MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp
        Timber.i("Sign Up Activity started...")

        binding.singupBtn.setOnClickListener {
            val enteredName = binding.name.text.toString()
            val enteredUsername = binding.username.text.toString()
            val enteredPassword = binding.password.text.toString()


            if (enteredUsername.length > 3 && enteredPassword.length > 5 && enteredUsername != null && enteredPassword != null) {
                Toast.makeText(this, "Login successfully", Toast.LENGTH_LONG).show()
                Timber.i("SignUp successfull")
                val newUser = UserModel(
                    name = enteredName,
                    email = enteredUsername,
                    password = enteredPassword

                )
                Timber.i("User: $newUser Created")
                app.users.create(newUser)
                val intent = Intent(this, LoginView::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Add a Valid Password and Username", Toast.LENGTH_LONG).show()
            }
        }
    }
}