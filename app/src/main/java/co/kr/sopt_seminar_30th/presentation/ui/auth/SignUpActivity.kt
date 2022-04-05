package co.kr.sopt_seminar_30th.presentation.ui.auth

import android.content.Intent
import android.os.Bundle
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.ActivitySignUpBinding
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseActivity

class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {
    override val TAG: String
        get() = SignUpActivity::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.activity_sign_up

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUp()
    }

    private fun signUp() {
        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java).apply {
                putExtra("userId", binding.etUserId.text.toString())
            }
            setResult(RESULT_OK, intent)
            if (!isFinishing) {
                finish()
            }
        }
    }
}