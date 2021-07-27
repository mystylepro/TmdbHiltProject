package com.app.daggerhiltsapplicationtmdb.utils

import android.content.Context
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPreference @Inject constructor(@ApplicationContext context: Context) {

    val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun isLogin(): String {
        return prefs.getString("LOGIN_KEY", "")!!
    }

    fun setLoginUser(query: String) {
        prefs.edit().putString("LOGIN_KEY", query).apply()
    }
}