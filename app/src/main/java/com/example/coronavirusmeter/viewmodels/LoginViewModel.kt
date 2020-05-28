package com.example.coronavirusmeter.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(application: Application)  : BaseViewModel(application){

    private lateinit var auth :FirebaseAuth
    var userEmail  = MutableLiveData<String>()
    var userPassword = MutableLiveData<String>()

    var isError = MutableLiveData<Boolean>()
    var isSuccessful = MutableLiveData<Boolean>()
    var errorMessage = MutableLiveData<String>()

    fun login(){
        auth = FirebaseAuth.getInstance()

        if(userEmail != null && userPassword != null){
            auth.signInWithEmailAndPassword(userEmail.value!!,userPassword.value!!).addOnCompleteListener {task ->
            if(task.isComplete && task.isSuccessful){
                isError.value = false
                isSuccessful.value = true
            }
            }.addOnFailureListener { exception ->
                isError.value = true
                isSuccessful.value = false
                errorMessage.value = exception.localizedMessage.toString()
            }
        }

    }

}