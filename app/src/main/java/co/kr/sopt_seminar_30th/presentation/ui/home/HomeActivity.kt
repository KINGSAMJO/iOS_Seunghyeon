package co.kr.sopt_seminar_30th.presentation.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.ActivityHomeBinding
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override val TAG: String
        get() = HomeActivity::class.java.simpleName

    override val layoutRes: Int
        get() = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}