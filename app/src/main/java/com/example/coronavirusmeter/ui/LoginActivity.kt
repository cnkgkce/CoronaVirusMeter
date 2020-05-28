package com.example.coronavirusmeter.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.coronavirusmeter.R
import com.example.coronavirusmeter.viewmodels.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var viewModel : LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        signInButton.setOnClickListener { signIn() }
        observeLiveData()

    }



    fun toSignUpActivity(view:View){
        val intent = Intent(this,SignUpActivity::class.java)
        startActivity(intent)
    }

    fun signIn(){
        var userEmail = userEmailText.text.toString()
        var userPassword = userPasswordText.text.toString()
        viewModel.userEmail.value = userEmail
        viewModel.userPassword.value = userPassword
        viewModel.login()

    }

    fun observeLiveData(){
        viewModel.isError.observe(this, Observer {isError->
            if(isError){
                var errorMessage = viewModel.errorMessage.value
               try {
                   Toast.makeText(applicationContext,errorMessage!!,Toast.LENGTH_LONG).show()
               }catch (e : Exception){
                   e.printStackTrace()
               }

            }

        })

        viewModel.isSuccessful.observe(this, Observer {isSuccessful->
            if(isSuccessful){
                //FeedActivitye gidilecek
                Toast.makeText(applicationContext,"Welcome" + " "+ auth.currentUser?.email.toString(),Toast.LENGTH_LONG).show()
                val intent = Intent(this,FeedActivity::class.java)
                startActivity(intent)
            }

        })
    }

}
