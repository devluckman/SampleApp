package com.edu.filmku.presentation.login

import android.content.Intent
import androidx.activity.viewModels
import com.edu.filmku.core.base.BaseActivity
import com.edu.filmku.data.network.Resource
import com.edu.filmku.databinding.ActivityLoginBinding
import com.edu.filmku.presentation.main.MainActivity
import com.edu.filmku.presentation.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(
    ActivityLoginBinding::inflate
) {

    // TODO(6) Setup firebase add method sign in

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewReady() {
        viewModel.loginState.observe(this) {
            // TODO(7) Create Handle Toast and Loading
            when (it) {
                is Resource.Success -> {
                    setLoading(false)
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
                is Resource.Error -> {
                    setLoading(false)
                    showMessage(it.message)
                }
                is Resource.Loading -> setLoading(true)
            }
        }

        binding.apply {
            btnLogin.setOnClickListener {
                doLoginWithEmailPassword()
            }
            btnRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
                finish()
            }
        }
    }

    private fun doLoginWithEmailPassword() {
        // TODO(8) Create Logic Validation Email Password
        binding.apply {
            var isValid = true
            val email : String = edtEmail.editText.text.toString()
            val password : String = edtPassword.editText.text.toString()

            if (email.isEmpty()) {
                isValid = false
                edtEmail.setError("Please enter your email")
            }

            if (password.isEmpty()) {
                isValid = false
                edtPassword.setError("Please enter your password")
            }

            if (isValid) viewModel.loginWithEmailPassword(email, password)
        }
    }
}