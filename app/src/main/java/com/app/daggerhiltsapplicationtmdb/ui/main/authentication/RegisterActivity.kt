package com.app.daggerhiltsapplicationtmdb.ui.main.authentication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.daggerhiltsapplicationtmdb.databinding.ActivityRegisterBinding
import com.app.daggerhiltsapplicationtmdb.ui.main.authentication.viewmodel.RegisterViewModel
import com.app.daggerhiltsapplicationtmdb.ui.main.launching.HomeActivity
import com.app.daggerhiltsapplicationtmdb.utils.MyPreference
import com.app.daggerhiltsapplicationtmdb.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private val viewBinding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var myPreference: MyPreference
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewBinding.loginBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        viewBinding.signupBtn.setOnClickListener {
            getCreateNewUser(
                viewBinding.emailEdtText.text.toString(),
                viewBinding.passEdtText.text.toString()
            )
        }
    }

    private fun getCreateNewUser(
        email: String, password: String
    ) {
        registerViewModel.getRegister(
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
                            Toast.makeText(this, "Yes my new user created ", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            Toast.makeText(this, it.data.exception.toString(), Toast.LENGTH_LONG)
                                .show()
                        }
                        viewBinding.progressBar.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        viewBinding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        viewBinding.progressBar.visibility = View.VISIBLE
                    }
                }
            }

        })
    }
}