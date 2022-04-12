package co.kr.sopt_seminar_30th.presentation.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.ActivityHomeBinding
import co.kr.sopt_seminar_30th.domain.entity.follower.FollowerInformation
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
        val followerList = listOf<FollowerInformation>(
            FollowerInformation("한진희", "한진희입니다.", null),
            FollowerInformation("곽호택", "곽호택입니다.", null),
            FollowerInformation("권용민", "권용민입니다.", null),
            FollowerInformation("김세훈", "김세훈입니다.", null),
            FollowerInformation("김수빈", "김수빈입니다.", null),
            FollowerInformation("김효림", "김효림입니다.", null),
            FollowerInformation("문다빈", "문다빈입니다.", null),
            FollowerInformation("문명주", "문명주입니다.", null),
            FollowerInformation("박세은", "박세은입니다.", null),
            FollowerInformation("심채영", "심채영입니다.", null),
            FollowerInformation("이강민", "이강민입니다.", null),
            FollowerInformation("이창환", "이창환입니다.", null),
            FollowerInformation("이혜빈", "이혜빈입니다.", null),
            FollowerInformation("정설희", "정설희입니다.", null),
            FollowerInformation("조재훈", "조재훈입니다.", null)
        )
        homeViewModel.insertFollowerList(followerList)
    }

    private fun insertInitRepository() {

    }
}