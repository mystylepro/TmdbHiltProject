package com.app.daggerhiltsapplicationtmdb.ui.main.authentication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.daggerhiltsapplicationtmdb.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {
    fun getRegister(emailId: String, password: String) = liveData(Dispatchers.IO)
    {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = firebaseAuth.createUserWithEmailAndPassword(
                        emailId,
                        password
                    )
                )
            )
        } catch (error: Exception) {
            emit(Resource.error(data = null, message = error.message ?: "Error Occurred"))
        }
    }
}