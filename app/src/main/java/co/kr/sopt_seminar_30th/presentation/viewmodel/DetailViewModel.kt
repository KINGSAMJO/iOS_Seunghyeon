package co.kr.sopt_seminar_30th.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.kr.sopt_seminar_30th.domain.entity.follower.FollowerInformation

class DetailViewModel: ViewModel() {
    private var _follower = MutableLiveData<FollowerInformation>()
    val follower: LiveData<FollowerInformation> get() = _follower

    fun setFollower(name: String, description: String, image: String?) {
        _follower.value = FollowerInformation(name, description, image)
    }
}