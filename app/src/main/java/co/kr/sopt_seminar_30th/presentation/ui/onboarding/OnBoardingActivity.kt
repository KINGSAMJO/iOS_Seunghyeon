package co.kr.sopt_seminar_30th.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.data.datasource.local.SopthubDataStore
import co.kr.sopt_seminar_30th.databinding.ActivityOnBoardingBinding
import co.kr.sopt_seminar_30th.presentation.ui.auth.SignInActivity
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseActivity
import co.kr.sopt_seminar_30th.presentation.ui.home.HomeActivity
import co.kr.sopt_seminar_30th.presentation.viewmodel.OnBoardingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_on_boarding

    private val viewModel by viewModels<OnBoardingViewModel>()

    @Inject
    lateinit var dataStore: SopthubDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkAutoLoginEnabled()
        checkOnBoardingEnabled()
        observeData()
    }

    private fun checkAutoLoginEnabled() {
        if (dataStore.autoLogin) {
            viewModel.checkAuthorization(dataStore.userId)
        }
    }

    private fun checkOnBoardingEnabled() {
        if (dataStore.onBoardingEnabled) {
            if (!isFinishing) {
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            }
        }
    }

    private fun observeData() {
        viewModel.autoLoginState
            .flowWithLifecycle(this.lifecycle)
            .onEach {
                if (it) {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
            }.launchIn(this.lifecycleScope)
    }
}