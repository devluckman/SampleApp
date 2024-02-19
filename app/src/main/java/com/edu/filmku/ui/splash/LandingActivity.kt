package com.edu.filmku.ui.splash

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.edu.filmku.core.BaseActivity
import com.edu.filmku.databinding.ActivityLandingBinding
import com.edu.filmku.ui.login.LoginActivity
import com.edu.filmku.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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