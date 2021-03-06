package co.kr.sopt_seminar_30th.presentation.ui.home.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.add
import androidx.fragment.app.commit
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.FragmentHomeBinding
import co.kr.sopt_seminar_30th.presentation.ui.adapter.HomeViewPagerAdapter
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseFragment
import co.kr.sopt_seminar_30th.presentation.ui.home.profile.MyProfileFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val TAG: String
        get() = HomeFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_home

    private var _homeViewPagerAdapter: HomeViewPagerAdapter? = null
    private val homeViewPagerAdapter get() = _homeViewPagerAdapter ?: error("Adapter not initialized")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragmentContainerView()
        initViewPager()
    }

    override fun onDestroyView() {
        _homeViewPagerAdapter = null
        super.onDestroyView()
    }

    private fun initFragmentContainerView() {
        childFragmentManager.apply {
            if (fragments.isEmpty()) {
                commit {
                    add<MyProfileFragment>(R.id.fcv_home_top, MY_PROFILE_FRAGMENT)
                }
            }
        }
    }

    private fun initViewPager() {
        _homeViewPagerAdapter =
            HomeViewPagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)
        binding.vpHome.adapter = homeViewPagerAdapter

        TabLayoutMediator(binding.tlHome, binding.vpHome) { tab, position ->
            tab.text = tabText[position]
        }.attach()
    }

    companion object {
        private val MY_PROFILE_FRAGMENT = MyProfileFragment::class.java.simpleName
        private val tabText = listOf("FOLLOWER", "FOLLOWING")
    }
}