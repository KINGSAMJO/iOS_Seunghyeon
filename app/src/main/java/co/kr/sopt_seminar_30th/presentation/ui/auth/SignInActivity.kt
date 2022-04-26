package co.kr.sopt_seminar_30th.presentation.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.ActivitySignInBinding
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseActivity
import co.kr.sopt_seminar_30th.presentation.ui.home.HomeActivity
import co.kr.sopt_seminar_30th.presentation.viewmodel.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_sign_in

    private val signInViewModel by viewModels<SignInViewModel>()

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

        signUp()
        login()
        observeLogin()
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

        signInViewModel.isEmpty.observe(this) {
            if (it) {
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            }
        }

        signInViewModel.autoLogin.observe(this) {
            if (it) {
                Toast.makeText(this, "자동로그인 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}