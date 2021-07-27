package com.app.daggerhiltsapplicationtmdb.ui.main.launching

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.daggerhiltsapplicationtmdb.databinding.ActivitySplashScreenBinding
import com.app.daggerhiltsapplicationtmdb.ui.main.authentication.LoginActivity
import com.app.daggerhiltsapplicationtmdb.utils.MyPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashScreen : AppCompatActivity() {
    private val viewBinding: ActivitySplashScreenBinding by lazy {
        ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var preference: MyPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            navigateScreen()
        }
    }

    private fun navigateScreen() {
        if (preference.isLogin().contentEquals("Login")) {
            startActivity(
                Intent(
                    this,
                    HomeActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
            finish()
        } else {
            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
            finish()
        }
    }
}