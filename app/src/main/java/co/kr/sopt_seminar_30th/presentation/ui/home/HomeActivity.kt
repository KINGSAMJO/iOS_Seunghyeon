package co.kr.sopt_seminar_30th.presentation.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.ActivityHomeBinding
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseActivity
import co.kr.sopt_seminar_30th.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_home

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewmodel = homeViewModel
        initBottomNavigationView()
    }

    private fun initBottomNavigationView() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_home) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.bnvHome.setupWithNavController(navController)
    }
}