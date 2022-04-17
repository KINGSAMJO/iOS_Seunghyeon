package co.kr.sopt_seminar_30th.presentation.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.ActivityHomeBinding
import co.kr.sopt_seminar_30th.domain.entity.follower.FollowerInformation
import co.kr.sopt_seminar_30th.domain.entity.repository.RepositoryInformation
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
        binding.lifecycleOwner = this

        insertInitFollower()
        insertInitRepository()
    }

    private fun insertInitFollower() {
        val nameList = listOf<String>(
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
        for(i in nameList.indices) {
            followerList.add(FollowerInformation(nameList[i], "${nameList[i]}입니다.", null, i))
        }
        homeViewModel.insertFollowerList(followerList)
    }

    private fun insertInitRepository() {
        val repositoryList = listOf<RepositoryInformation>(
            RepositoryInformation("SOPT_30th_Seminar", "30기 세미나 과제 레포지토리입니다."),
            RepositoryInformation("SOPT_29th_Seminar", "29기 세미나 과제 레포지토리입니다."),
            RepositoryInformation("SOPT_28th_Seminar", "28기 세미나 과제 레포지토리입니다."),
        )
        homeViewModel.insertRepositoryList(repositoryList)
    }
}