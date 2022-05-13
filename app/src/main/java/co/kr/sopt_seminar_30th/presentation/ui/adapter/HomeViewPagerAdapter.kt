package co.kr.sopt_seminar_30th.presentation.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import co.kr.sopt_seminar_30th.presentation.ui.home.home.HomeFollowerFragment
import co.kr.sopt_seminar_30th.presentation.ui.home.home.HomeFollowingFragment

class HomeViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            FragmentPosition.FOLLOWER_FRAGMENT.position -> HomeFollowerFragment()
            FragmentPosition.FOLLOWING_FRAGMENT.position -> HomeFollowingFragment()
            else -> throw IndexOutOfBoundsException()
        }
    }

    enum class FragmentPosition(val position: Int) {
        FOLLOWER_FRAGMENT(0), FOLLOWING_FRAGMENT(1)
    }
}