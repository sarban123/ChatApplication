package com.example.projectchatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var edtname: EditText
    private lateinit var edtemail: EditText
    private lateinit var edtpass: EditText
    private lateinit var btSignUp: Button

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edtname = findViewById(R.id.edname)
        edtemail = findViewById(R.id.edemail)
        edtpass = findViewById(R.id.edpass)
        btSignUp = findViewById(R.id.btnsignup)

        btSignUp.setOnClickListener{
            val name=edtname.text.toString()
            val email = edtemail.text.toString()
            val password = edtpass.text.toString()

            signUp(name,email,password)
        }
    }
    private fun signUp(name:String, email: String, password: String){
        //logic to create user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                   //code to home
                  addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                   val intent = Intent(this@SignUp, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@SignUp, "Some error", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDatabase(name: String, email: String, uid: String){
        mDbRef = FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue(User(name,email,uid))

    }

}