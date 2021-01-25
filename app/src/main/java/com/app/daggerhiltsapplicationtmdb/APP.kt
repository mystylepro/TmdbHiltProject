package com.app.daggerhiltsapplicationtmdb

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class APP : Application()
{
    companion object {
        lateinit var auth: FirebaseAuth
    }

    override fun onCreate() {
        super.onCreate()
        auth = FirebaseAuth.getInstance()
    }
}