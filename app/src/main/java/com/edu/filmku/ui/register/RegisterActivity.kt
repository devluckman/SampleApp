package com.edu.filmku.ui.register

import android.content.Intent
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.activity.viewModels
import com.edu.filmku.core.BaseActivity
import com.edu.filmku.data.network.Resource
import com.edu.filmku.databinding.ActivityRegisterBinding
import com.edu.filmku.domain.request.RequestRegister
import com.edu.filmku.ui.login.LoginViewModel
import com.edu.filmku.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding>(
    ActivityRegisterBinding::inflate
) {
    // TODO (9) Setup FireStore
    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewReady() {
        viewModel.registerState.observe(this) {
            when(it) {
                is Resource.Success -> {
                    setLoading(false)
                    startActivity(Intent(this, MainActivity::class.java))
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
            val fullText = "By continuing, you agree to our Terms of Service and Privacy Policy."
            val targetTexts = listOf("Terms of Service", "Privacy Policy")
            highlightText(tvTnc, fullText, targetTexts)
            btnRegister.setOnClickListener { doRegister() }
        }

    }

    private fun highlightText(textView: TextView, fullText: String, targetTexts: List<String>) {
        val spannable = SpannableString(fullText)
        for (targetText in targetTexts) {
            val startIndex = fullText.indexOf(targetText)
            if (startIndex != -1) {
                val endIndex = startIndex + targetText.length
                spannable.setSpan(
                    ForegroundColorSpan(Color.BLUE),
                    startIndex,
                    endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        textView.text = spannable
    }

    private fun doRegister() {
        binding.apply {
            var isValid = true
            val firstName = edtFirstName.editText.text.toString()
            val email: String = edtEmail.editText.text.toString()
            val password: String = edtPassword.editText.text.toString()
            val confirmPass: String = edtConfirmPassword.editText.text.toString()
            val fullName: String = String.format(
                "%s %s", firstName,
                edtLastName.editText.text.toString()
            )

            if (firstName.isEmpty()) {
                isValid = false
                edtFirstName.setError("Please enter your name")
            }

            if (email.isEmpty()) {
                isValid = false
                edtEmail.setError("Please enter your email")
            }

            if (password.isEmpty()) {
                isValid = false
                edtPassword.setError("Please enter your password")
            }

            if (confirmPass.isEmpty()) {
                isValid = false
                edtConfirmPassword.setError("Please enter your confirm password")
            }

            if (confirmPass != password) {
                isValid = false
                edtConfirmPassword.setError("Your password not match")
            }

            if (isValid) viewModel.register(
                RequestRegister(
                    name = fullName,
                    email = email,
                    password = password
                )
            )
        }
    }
}