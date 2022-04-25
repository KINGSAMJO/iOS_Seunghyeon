package co.kr.sopt_seminar_30th.presentation.ui.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.FragmentProfileBinding
import co.kr.sopt_seminar_30th.domain.entity.follower.FollowerInformation
import co.kr.sopt_seminar_30th.domain.entity.repository.RepositoryInformation
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseFragment
import co.kr.sopt_seminar_30th.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override val TAG: String
        get() = ProfileFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_profile

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
        initData()
        fold()
        changeToFollowerFragment()
        changeToRepositoryFragment()
    }

    private fun initFragmentContainerView() {
        childFragmentManager.apply {
            if (fragments.isEmpty()) {
                commit {
                    add<MyProfileFragment>(R.id.fcv_profile_top, MY_PROFILE_FRAGMENT)
                    add<ProfileFollowerFragment>(R.id.fcv_profile_bottom, FOLLOWER_FRAGMENT)
                }
            }
        }
        binding.btnProfileFollower.isSelected = true
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
            binding.fcvProfileTop.apply {
                visibility = when (this.visibility) {
                    View.VISIBLE -> View.GONE
                    else -> View.VISIBLE
                }
            }
        }
    }

    private fun changeToFollowerFragment() {
        binding.btnProfileFollower.setOnClickListener {
            val followerFragment = childFragmentManager.findFragmentByTag(FOLLOWER_FRAGMENT)
            val repositoryFragment = childFragmentManager.findFragmentByTag(REPOSITORY_FRAGMENT)

            childFragmentManager.commit {
                setReorderingAllowed(true)
                repositoryFragment?.let { hide(it) }
                followerFragment?.let { show(it) }
                    ?: add<ProfileFollowerFragment>(R.id.fcv_profile_bottom, FOLLOWER_FRAGMENT)
            }
            binding.btnProfileFollower.isSelected = true
            binding.btnProfileRepository.isSelected = false
        }
    }

    private fun changeToRepositoryFragment() {
        binding.btnProfileRepository.setOnClickListener {
            val followerFragment = childFragmentManager.findFragmentByTag(FOLLOWER_FRAGMENT)
            val repositoryFragment = childFragmentManager.findFragmentByTag(REPOSITORY_FRAGMENT)

            childFragmentManager.commit {
                setReorderingAllowed(true)
                followerFragment?.let { hide(it) }
                repositoryFragment?.let { show(it) }
                    ?: add<ProfileRepositoryFragment>(R.id.fcv_profile_bottom, REPOSITORY_FRAGMENT)
            }
            binding.btnProfileFollower.isSelected = false
            binding.btnProfileRepository.isSelected = true
        }
    }

    companion object {
        val MY_PROFILE_FRAGMENT: String = MyProfileFragment::class.java.simpleName
        val FOLLOWER_FRAGMENT: String = ProfileFollowerFragment::class.java.simpleName
        val REPOSITORY_FRAGMENT: String = ProfileRepositoryFragment::class.java.simpleName

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