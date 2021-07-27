package com.app.daggerhiltsapplicationtmdb.ui.main.authentication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.daggerhiltsapplicationtmdb.databinding.ActivityLoginBinding
import com.app.daggerhiltsapplicationtmdb.ui.main.authentication.viewmodel.LoginViewModel
import com.app.daggerhiltsapplicationtmdb.ui.main.launching.HomeActivity
import com.app.daggerhiltsapplicationtmdb.utils.MyPreference
import com.app.daggerhiltsapplicationtmdb.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var myPreference: MyPreference
    private val viewBinding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewBinding.registerBtn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        viewBinding.loginBtn.setOnClickListener {
            getLoginUser(
                viewBinding.emailEdtTextForLogin.text.toString(),
                viewBinding.passEdtTextForLogin.text.toString()
            )
        }
    }

    private fun getLoginUser(
        email: String, password: String
    ) {
        loginViewModel.getLogin(
            email,
            password
        ).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        if (it.data?.isSuccessful!!) {
                            myPreference.setLoginUser("Login")
                            startActivity(
                                Intent(this, HomeActivity::class.java)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            )
                            finish()
                        } else {
                            Toast.makeText(this, it.data.exception.toString(), Toast.LENGTH_LONG)
                                .show()
                        }

                        viewBinding.progressBar.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()

                        viewBinding.progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        viewBinding.progressBar.visibility = View.VISIBLE
                    }
                }
            }

        })
    }
}