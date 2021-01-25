package com.app.daggerhiltsapplicationtmdb.ui.main.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.daggerhiltsapplicationtmdb.R
import com.app.daggerhiltsapplicationtmdb.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private val viewBinding : ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
    }
}