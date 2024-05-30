package com.edu.filmku.presentation.splash

import android.content.Intent
import androidx.activity.viewModels
import com.edu.filmku.core.base.BaseActivity
import com.edu.filmku.databinding.ActivityLandingBinding
import com.edu.filmku.presentation.login.LoginActivity
import com.edu.filmku.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingActivity : BaseActivity<ActivityLandingBinding>(
    ActivityLandingBinding::inflate
) {

    //    TODO (3) Use AppCompatActivity before create BaseActivity
    private val viewModel: LandingViewModel by viewModels()
    override fun onViewReady() {
        viewModel.isLogged.observe(this) { isLogged ->
            if (isLogged) {
                startActivity(Intent(this@LandingActivity, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@LandingActivity, LoginActivity::class.java))
                finish()
            }
        }
    }

}