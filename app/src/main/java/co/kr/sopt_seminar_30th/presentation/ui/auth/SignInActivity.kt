package co.kr.sopt_seminar_30th.presentation.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.data.datasource.local.SopthubDataStore
import co.kr.sopt_seminar_30th.databinding.ActivitySignInBinding
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseActivity
import co.kr.sopt_seminar_30th.presentation.ui.home.HomeActivity
import co.kr.sopt_seminar_30th.presentation.viewmodel.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_sign_in

    private val signInViewModel by viewModels<SignInViewModel>()

    @Inject
    lateinit var dataStore: SopthubDataStore

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val userId = it.data?.getStringExtra("userId") ?: ""
                val userPassword = it.data?.getStringExtra("userPassword") ?: ""
                binding.etUserId.setText(userId)
                binding.etUserPassword.setText(userPassword)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewmodel = signInViewModel
        binding.lifecycleOwner = this

        loginWithAutoLogin()
        signUp()
        login()
        observeLogin()
    }

    private fun loginWithAutoLogin() {
        if (dataStore.autoLogin) {
            signInViewModel.checkAuthorization(dataStore.userId)
        }
    }

    private fun signUp() {
        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            activityResultLauncher.launch(intent)
        }
    }

    private fun login() {
        binding.btnSignIn.setOnClickListener {
            signInViewModel.login()
        }
    }

    private fun observeLogin() {
        signInViewModel.isSuccess.observe(this) {
            when (it) {
                true -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else -> {
                    Toast.makeText(this, "다시 시도해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        }
        signInViewModel.isEmailIncorrect.observe(this) {
            if (it) {
                Toast.makeText(this, "존재하지 않는 계정입니다", Toast.LENGTH_SHORT).show()
                binding.etUserPassword.text.clear()
            }
        }
        signInViewModel.isPasswordIncorrect.observe(this) {
            if (it) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
                binding.etUserPassword.text.clear()
            }
        }
        signInViewModel.isEmpty.observe(this) {
            if (it) {
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            }
        }
        signInViewModel.autoLoginState
            .flowWithLifecycle(this.lifecycle)
            .onEach {
                if(it) {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
            }.launchIn(this.lifecycleScope)
    }
}