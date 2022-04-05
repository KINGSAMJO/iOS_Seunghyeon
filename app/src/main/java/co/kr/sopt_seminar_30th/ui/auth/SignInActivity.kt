package co.kr.sopt_seminar_30th.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.ActivitySignInBinding
import co.kr.sopt_seminar_30th.ui.base.BaseActivity

class SignInActivity : BaseActivity<ActivitySignInBinding>() {
    override val TAG: String
        get() = SignInActivity::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.activity_sign_in

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val userId = it.data?.getStringExtra("userId") ?: ""
                binding.etUserId.setText(userId)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUp()
    }

    private fun signUp() {
        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            activityResultLauncher.launch(intent)
        }
    }
}