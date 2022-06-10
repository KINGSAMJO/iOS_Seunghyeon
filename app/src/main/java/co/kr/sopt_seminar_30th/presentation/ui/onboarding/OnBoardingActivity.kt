package co.kr.sopt_seminar_30th.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.data.datasource.local.SopthubDataStore
import co.kr.sopt_seminar_30th.databinding.ActivityOnBoardingBinding
import co.kr.sopt_seminar_30th.presentation.ui.auth.SignInActivity
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseActivity
import co.kr.sopt_seminar_30th.presentation.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_on_boarding

    @Inject
    lateinit var dataStore: SopthubDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkAutoLoginEnabled()
        checkOnBoardingEnabled()
    }

    private fun checkAutoLoginEnabled() {
        if (dataStore.autoLogin) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun checkOnBoardingEnabled() {
        if (dataStore.onBoardingEnabled) {
            if(!isFinishing) {
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            }
        }
    }
}