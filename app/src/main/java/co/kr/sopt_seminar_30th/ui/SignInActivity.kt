package co.kr.sopt_seminar_30th.ui

import android.os.Bundle
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.ActivitySignInBinding
import co.kr.sopt_seminar_30th.ui.base.BaseActivity

class SignInActivity : BaseActivity<ActivitySignInBinding>() {
    override val TAG: String
        get() = SignInActivity::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.activity_sign_in

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}