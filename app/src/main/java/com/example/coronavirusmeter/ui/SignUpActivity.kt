package com.example.coronavirusmeter.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.coronavirusmeter.R
import com.example.coronavirusmeter.viewmodels.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.lang.Exception
import kotlin.math.sign

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var viewModel : SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)
        auth = FirebaseAuth.getInstance()

        signUpButton.setOnClickListener { signUp() }
        observeLiveData()
    }


    fun signUp(){
        var email = signUpEmailText.text.toString()
        var password = signUpPasswordText.text.toString()
        viewModel.userEmail.value = email
        viewModel.userPassword.value = password
        viewModel.signUp()
    }

    fun observeLiveData(){
        viewModel.isSuccessful.observe(this, Observer {isSuccessful->
            if(isSuccessful){
                //başarılı
                Toast.makeText(applicationContext,"User is created",Toast.LENGTH_LONG).show()
                val intent = Intent(this,FeedActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                //Başarısız
                try {
                    var errorMessage = viewModel.errorMessage.value
                    Toast.makeText(applicationContext,errorMessage,Toast.LENGTH_LONG).show()
                }catch (e : Exception){
                    e.printStackTrace()
                }
            }

        })
    }
}
