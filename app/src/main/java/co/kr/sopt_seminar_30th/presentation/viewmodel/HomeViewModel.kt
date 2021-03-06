package co.kr.sopt_seminar_30th.presentation.viewmodel

import android.annotation.SuppressLint
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.sopt_seminar_30th.domain.entity.home.UserFollow
import co.kr.sopt_seminar_30th.domain.entity.home.UserProfile
import co.kr.sopt_seminar_30th.domain.entity.home.UserRepository
import co.kr.sopt_seminar_30th.domain.repository.remote.HomeRepository
import co.kr.sopt_seminar_30th.domain.usecase.user.GetUserIdUseCase
import co.kr.sopt_seminar_30th.domain.usecase.user.TurnOffAutoLoginUseCase
import co.kr.sopt_seminar_30th.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("NullSafeMutableLiveData")
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val turnOffAutoLoginUseCase: TurnOffAutoLoginUseCase,
    private val homeRepository: HomeRepository
) : ViewModel() {
    private var id: String = ""

    private var _follower = MutableLiveData<List<UserFollow>>()
    val follower: LiveData<List<UserFollow>> get() = _follower

    private var _following = MutableLiveData<List<UserFollow>>()
    val following: LiveData<List<UserFollow>> get() = _following

    private var _repository = MutableLiveData<List<UserRepository>>()
    val repository: LiveData<List<UserRepository>> get() = _repository

    private var _user = MutableLiveData<UserProfile>()
    val user: LiveData<UserProfile> get() = _user

    var userAge = MutableLiveData<String?>()
    var userMbti = MutableLiveData<String?>()
    var userDescription = MutableLiveData<String?>()
    var userImage = MutableLiveData<Uri?>()

    private var _updateSuccess = SingleLiveEvent<Boolean>()
    val updateSuccess: LiveData<Boolean> get() = _updateSuccess

    private var _turnOffSuccess = SingleLiveEvent<Boolean>()
    val turnOffSuccess: LiveData<Boolean> get() = _turnOffSuccess

    fun setId(userId: String) {
        id = userId
    }

    fun getFollowerList() {
        viewModelScope.launch {
            when (id) {
                "" -> {
                    getUserIdUseCase()
                        .onSuccess {
                            homeRepository.fetchUserFollowers(id)
                                .onSuccess { list ->
                                    _follower.value = list
                                }
                                .onFailure { exception ->
                                    Timber.e(exception)
                                }
                        }
                }
                else -> {
                    homeRepository.fetchUserFollowers(id)
                        .onSuccess { list ->
                            _follower.value = list
                        }
                        .onFailure { exception ->
                            Timber.e(exception)
                        }
                }
            }
        }
    }

    fun updateFollowerList(followerList: List<UserFollow>) {
        viewModelScope.launch {
            kotlin.runCatching {
                // TODO: 5, 6??? ??? ???????????? ?????? ???????????? Room ???????????? ?????????????????? ??????
            }.onFailure {
                Timber.e(it)
            }
        }
    }

    fun deleteFollower(follower: UserFollow) {
        viewModelScope.launch {
            kotlin.runCatching {
                // TODO: 5, 6??? ??? ???????????? ?????? ???????????? Room ???????????? ?????????????????? ??????
            }.onFailure {
                Timber.e(it)
            }
        }
    }

    fun getFollowingList() {
        viewModelScope.launch {
            when (id) {
                "" -> {
                    getUserIdUseCase()
                        .onSuccess {
                            homeRepository.fetchUserFollowing(id)
                                .onSuccess { list ->
                                    _following.value = list
                                }
                                .onFailure { exception ->
                                    Timber.e(exception)
                                }
                        }
                }
                else -> {
                    homeRepository.fetchUserFollowing(id)
                        .onSuccess { list ->
                            _following.value = list
                        }
                        .onFailure { exception ->
                            Timber.e(exception)
                        }
                }
            }
        }
    }

    fun getRepositoryList() {
        viewModelScope.launch {
            when (id) {
                "" -> {
                    getUserIdUseCase()
                        .onSuccess {
                            homeRepository.fetchUserRepositories(id)
                                .onSuccess { list ->
                                    _repository.value = list
                                }
                                .onFailure { exception ->
                                    Timber.e(exception)
                                }
                        }
                }
                else -> {
                    homeRepository.fetchUserRepositories(id)
                        .onSuccess { list ->
                            _repository.value = list
                        }
                        .onFailure { exception ->
                            Timber.e(exception)
                        }
                }
            }
        }
    }

    fun updateRepositoryList(repositoryList: List<UserRepository>) {
        viewModelScope.launch {
            kotlin.runCatching {
                // TODO: 5, 6??? ??? ???????????? ?????? ???????????? Room ???????????? ?????????????????? ??????
            }.onFailure {
                Timber.e(it)
            }
        }
    }

    fun deleteRepository(repository: UserRepository) {
        viewModelScope.launch {
            kotlin.runCatching {
                // TODO: 5, 6??? ??? ???????????? ?????? ???????????? Room ???????????? ?????????????????? ??????
            }.onFailure {
                Timber.e(it)
            }
        }
    }

    fun getUserInformation() {
        viewModelScope.launch {
            when (id) {
                "" -> {
                    getUserIdUseCase()
                        .onSuccess { userId ->
                            id = userId
                            homeRepository.fetchUserInformation(id)
                                .onSuccess { homeUserInformation ->
                                    _user.value = homeUserInformation
                                }
                                .onFailure { exception ->
                                    Timber.e(exception)
                                }
                        }.onFailure {
                            id = ""
                            error("Authorization error")
                        }
                }
                else -> {
                    homeRepository.fetchUserInformation(id)
                        .onSuccess { homeUserInformation ->
                            _user.value = homeUserInformation
                        }
                        .onFailure { exception ->
                            Timber.e(exception)
                        }
                }
            }
        }
    }

    fun editProfile() {
        // TODO: 5, 6??? ??? ???????????? ?????? ???????????? Room ???????????? ?????????????????? ??????
    }

    fun turnOffAutoLogin() {
        viewModelScope.launch {
            kotlin.runCatching {
                turnOffAutoLoginUseCase()
            }.onSuccess {
                _turnOffSuccess.value = true
            }
        }
    }
}