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
import co.kr.sopt_seminar_30th.domain.entity.follower.FollowerInformation
import co.kr.sopt_seminar_30th.domain.entity.repository.RepositoryInformation
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
        initData()
        fold()
        editProfile()
        changeToFollowerFragment()
        changeToRepositoryFragment()
    }

    private fun initFragmentContainerView() {
        childFragmentManager.apply {
            if (fragments.isEmpty()) {
                commit {
                    add<HomeFollowerFragment>(R.id.fcv_home_bottom, FOLLOWER_FRAGMENT)
                }
            }
        }
    }

    private fun initData() {
        binding.tvInit.setOnClickListener {
            val followerList = initFollowerList()
            val repositoryList = initRepositoryList()
            homeViewModel.initFollowerList(followerList)
            homeViewModel.initRepositoryList(repositoryList)
        }
    }

    private fun fold() {
        binding.tvFold.setOnClickListener {
            binding.layoutProfile.apply {
                visibility = when (this.visibility) {
                    View.VISIBLE -> View.GONE
                    else -> View.VISIBLE
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

        fun initFollowerList(): List<FollowerInformation> {
            val followerNameList = listOf<String>(
                "한진희",
                "곽호택",
                "권용민",
                "김세훈",
                "김수빈",
                "김효림",
                "문다빈",
                "문명주",
                "박세은",
                "심채영",
                "이강민",
                "이창환",
                "이혜빈",
                "정설희",
                "조재훈"
            )
            val followerList = mutableListOf<FollowerInformation>()
            for (i in followerNameList.indices) {
                followerList.add(
                    FollowerInformation(
                        followerNameList[i],
                        "${followerNameList[i]}입니다.",
                        null,
                        i
                    )
                )
            }
            return followerList
        }

        fun initRepositoryList(): List<RepositoryInformation> {
            val repositoryNameList = listOf<String>(
                "Charo-Android",
                "BeMyPlan-Android",
                "THE-SOPT-30th"
            )
            val repositoryList = mutableListOf<RepositoryInformation>()
            for (i in repositoryNameList.indices) {
                repositoryList.add(
                    RepositoryInformation(
                        repositoryNameList[i],
                        "${repositoryNameList[i]}의 레포지토리입니다.",
                        i
                    )
                )
            }
            return repositoryList
        }
    }
}