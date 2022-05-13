package co.kr.sopt_seminar_30th.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.sopt_seminar_30th.domain.entity.home.UserFollowInformation
import co.kr.sopt_seminar_30th.domain.entity.home.UserProfileInformation
import co.kr.sopt_seminar_30th.domain.entity.tmp.follower.FollowerInformation
import co.kr.sopt_seminar_30th.domain.repository.remote.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    private var _userId = ""
    val userId get() = _userId

    private var _user = MutableLiveData<UserProfileInformation>()
    val user: LiveData<UserProfileInformation> get() = _user

    private var _follower = MutableLiveData<List<UserFollowInformation>>()
    val follower: LiveData<List<UserFollowInformation>> get() = _follower

    private var _following = MutableLiveData<List<UserFollowInformation>>()
    val following: LiveData<List<UserFollowInformation>> get() = _following

    fun setUserId(userId: String) {
        _userId = userId
    }

    fun getUserInformation() {
        viewModelScope.launch {
            homeRepository.fetchUserInformation(userId)
                .onSuccess { user ->
                    _user.value = user
                }
                .onFailure {
                    error("who is $userId?")
                }
        }
    }

    fun getFollowerList() {
        viewModelScope.launch {
            homeRepository.fetchUserFollowers(userId)
                .onSuccess { list ->
                    _follower.value = list
                }
                .onFailure { exception ->
                    Timber.e(exception)
                }
        }
    }

    fun getFollowingList() {
        viewModelScope.launch {
            homeRepository.fetchUserFollowing(userId)
                .onSuccess { list ->
                    _following.value = list
                }
                .onFailure { exception ->
                    Timber.e(exception)
                }
        }
    }
}