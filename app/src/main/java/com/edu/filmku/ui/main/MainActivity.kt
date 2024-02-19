package com.edu.filmku.ui.main

import android.content.Intent
import androidx.activity.viewModels
import com.edu.filmku.core.BaseActivity
import com.edu.filmku.databinding.ActivityMainBinding
import com.edu.filmku.ui.splash.LandingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {

    private val viewModel : MainViewModel by viewModels()

    override fun onViewReady() {
        binding.btnLogin.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(this, LandingActivity::class.java))
            finish()
        }
    }
}