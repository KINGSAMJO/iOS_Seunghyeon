package co.kr.sopt_seminar_30th.presentation.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.ActivityDetailBinding
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseActivity
import co.kr.sopt_seminar_30th.presentation.ui.home.home.HomeFragment
import co.kr.sopt_seminar_30th.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_detail

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewmodel = homeViewModel

        initFragmentContainerView()
    }

    private fun initFragmentContainerView() {
        val userId = intent.getStringExtra("userId")
        userId?.let {
            homeViewModel.setId(userId)
        }
        homeViewModel.getUserInformation()
        homeViewModel.getFollowerList()
        homeViewModel.getFollowingList()
        supportFragmentManager.commit {
            add<HomeFragment>(R.id.fcv_detail, HOME_FRAGMENT)
        }
    }

    companion object {
        private val HOME_FRAGMENT = HomeFragment::class.java.simpleName
    }
}