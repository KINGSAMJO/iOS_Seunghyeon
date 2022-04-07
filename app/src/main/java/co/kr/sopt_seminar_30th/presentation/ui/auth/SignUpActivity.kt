package co.kr.sopt_seminar_30th.presentation.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.ActivitySignUpBinding
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseActivity
import co.kr.sopt_seminar_30th.presentation.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {
    override val TAG: String
        get() = SignUpActivity::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.activity_sign_up

    private val signUpViewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewmodel = signUpViewModel
        binding.lifecycleOwner = this

        signUp()
        observeSignUp()
    }

    private fun signUp() {
        binding.btnSignUp.setOnClickListener {
            signUpViewModel.signUp()
        }
    }

    private fun observeSignUp() {
        signUpViewModel.isSuccess.observe(this) {
            when (it) {
                true -> {
                    val intent = Intent(this, SignInActivity::class.java).apply {
                        putExtra("userId", binding.etUserId.text.toString())
                        putExtra("userPassword", binding.etUserPassword.text.toString())
                    }
                    setResult(RESULT_OK, intent)
                    if (!isFinishing) {
                        finish()
                    }
                }
                else -> {
                    Toast.makeText(this, "다시 시도해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        }

        signUpViewModel.isEmpty.observe(this) {
            if(it) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
}