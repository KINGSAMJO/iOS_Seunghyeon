package co.kr.sopt_seminar_30th.presentation.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.ActivityDetailBinding
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseActivity
import co.kr.sopt_seminar_30th.presentation.viewmodel.DetailViewModel

class DetailActivity : BaseActivity<ActivityDetailBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_detail

    private val detailViewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewmodel = detailViewModel

        setFollower()
    }

    private fun setFollower() {
        val name = intent.getStringExtra("name") ?: error("name must not null")
        val description = intent.getStringExtra("description") ?: error("description must not null")
        val image = intent.getStringExtra("image")
        detailViewModel.setFollower(name, description, image, 0)
    }
}