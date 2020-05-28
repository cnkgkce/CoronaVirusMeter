package com.example.coronavirusmeter.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel(application: Application)  : BaseViewModel(application) {
    private lateinit var auth : FirebaseAuth
    var userEmail = MutableLiveData<String>()
    var userPassword = MutableLiveData<String>()
    var isSuccessful = MutableLiveData<Boolean>()
    var errorMessage = MutableLiveData<String>()


    fun signUp(){
        auth = FirebaseAuth.getInstance()
        if(userEmail != null && userPassword != null){
            auth.createUserWithEmailAndPassword(userEmail.value!!,userPassword.value!!).addOnCompleteListener { task ->
                if(task.isSuccessful && task.isComplete){
                    isSuccessful.value = true
                }
            }.addOnFailureListener { exception ->
                if(exception != null){
                    errorMessage.value = exception.localizedMessage.toString() // For toast
                    isSuccessful.value = false
                }
            }

        }
        else{
            isSuccessful.value = false
        }

    }

}