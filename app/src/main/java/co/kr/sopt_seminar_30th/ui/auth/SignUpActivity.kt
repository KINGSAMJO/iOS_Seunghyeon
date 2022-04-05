package co.kr.sopt_seminar_30th.ui.auth

import android.os.Bundle
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.ActivitySignUpBinding
import co.kr.sopt_seminar_30th.ui.base.BaseActivity

class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {
    override val TAG: String
        get() = SignUpActivity::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.activity_sign_up

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}