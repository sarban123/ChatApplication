package com.example.projectchatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var edtemail: EditText
    private lateinit var edtpass: EditText
    private lateinit var btlogin: Button
    private lateinit var btSignUp: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edtemail = findViewById(R.id.edemail)
        edtpass = findViewById(R.id.edpass)
        btlogin = findViewById(R.id.btnlogin)
        btSignUp = findViewById(R.id.btnsignup)

        btSignUp.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
    }
        btlogin.setOnClickListener{
            val email = edtemail.text.toString()
            val password = edtpass.text.toString()

            login(email,password);
        }
    }
    private fun login(email: String, password: String){
        //logic to login user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code to login
                    val intent = Intent(this@Login, MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(this@Login, "user doesnot exist", Toast.LENGTH_SHORT).show()
                }
            }
    }
}