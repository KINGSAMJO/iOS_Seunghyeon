package co.kr.sopt_seminar_30th.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.FragmentHomeBinding
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseFragment
import co.kr.sopt_seminar_30th.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val TAG: String
        get() = HomeFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_home

    private val homeViewModel by activityViewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = homeViewModel
        initFragmentContainerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getUserInformation()
        editProfile()
        changeToFollowerFragment()
        changeToRepositoryFragment()
    }

    private fun initFragmentContainerView() {
        val followerFragment = childFragmentManager.findFragmentByTag(FOLLOWER_FRAGMENT)
        val repositoryFragment = childFragmentManager.findFragmentByTag(REPOSITORY_FRAGMENT)

        childFragmentManager.apply {
            if(fragments.isEmpty()) {
                commit {
                    add<HomeFollowerFragment>(R.id.fcv_home_bottom, FOLLOWER_FRAGMENT)
                }
            }
        }
    }

    private fun editProfile() {
        binding.btnEdit.setOnClickListener {
            parentFragmentManager.commit {
                replace<EditProfileFragment>(R.id.fcv_home, EDIT_PROFILE_FRAGMENT)
                addToBackStack(EDIT_PROFILE_FRAGMENT)
            }
        }
    }

    private fun changeToFollowerFragment() {
        binding.btnHomeFollower.setOnClickListener {
            val followerFragment = childFragmentManager.findFragmentByTag(FOLLOWER_FRAGMENT)
            val repositoryFragment = childFragmentManager.findFragmentByTag(REPOSITORY_FRAGMENT)

            childFragmentManager.commit {
                setReorderingAllowed(true)
                repositoryFragment?.let { hide(it) }
                followerFragment?.let { show(it) }
                    ?: add<HomeFollowerFragment>(R.id.fcv_home_bottom, FOLLOWER_FRAGMENT)
            }
        }
    }

    private fun changeToRepositoryFragment() {
        binding.btnHomeRepository.setOnClickListener {
            val followerFragment = childFragmentManager.findFragmentByTag(FOLLOWER_FRAGMENT)
            val repositoryFragment = childFragmentManager.findFragmentByTag(REPOSITORY_FRAGMENT)

            childFragmentManager.commit {
                setReorderingAllowed(true)
                followerFragment?.let { hide(it) }
                repositoryFragment?.let { show(it) }
                    ?: add<HomeRepositoryFragment>(R.id.fcv_home_bottom, REPOSITORY_FRAGMENT)
            }
        }
    }

    companion object {
        val EDIT_PROFILE_FRAGMENT: String = EditProfileFragment::class.java.simpleName
        val FOLLOWER_FRAGMENT: String = HomeFollowerFragment::class.java.simpleName
        val REPOSITORY_FRAGMENT: String = HomeRepositoryFragment::class.java.simpleName
    }
}