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
    override val TAG: String
        get() = SignInActivity::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.activity_sign_in

    private val signInViewModel by viewModels<SignInViewModel>()

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val userId = it.data?.getStringExtra("userId") ?: ""
                binding.etUserId.setText(userId)
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
    }
}