package com.app.daggerhiltsapplicationtmdb.di.module

import com.app.daggerhiltsapplicationtmdb.APP
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object FirebaseAuthenticationModule
{
    @ActivityScoped
    @Provides
    fun provideRegisterWithFireBase(email:String,password : String) : Task<AuthResult>
    {
        var taskResult: Task<AuthResult>? = null
        APP.auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            taskResult =  it
        }

        return taskResult!!
    }

    @ActivityScoped
    @Provides
    fun loginWithFireBase(email:String,password : String) : Boolean
    {
        var taskResult = false
        APP.auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            taskResult =  it.isSuccessful
        }

        return taskResult
    }
}