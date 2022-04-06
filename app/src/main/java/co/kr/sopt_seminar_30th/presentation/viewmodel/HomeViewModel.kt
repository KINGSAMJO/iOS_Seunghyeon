package co.kr.sopt_seminar_30th.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.sopt_seminar_30th.domain.entity.UserInformation
import co.kr.sopt_seminar_30th.domain.usecase.user.GetUserIdUseCase
import co.kr.sopt_seminar_30th.domain.usecase.user.GetUserInformationUseCase
import co.kr.sopt_seminar_30th.domain.usecase.user.UpdateUserInformationUseCase
import co.kr.sopt_seminar_30th.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val updateUserInformationUseCase: UpdateUserInformationUseCase
) : ViewModel() {
    private var _user = MutableLiveData<UserInformation>()
    val user: LiveData<UserInformation> get() = _user

    var userAge = MutableLiveData<String?>()
    var userMbti = MutableLiveData<String?>()
    var userDescription = MutableLiveData<String?>()

    private var _updateSuccess = SingleLiveEvent<Boolean>()
    val updateSuccess: LiveData<Boolean> get() = _updateSuccess

    fun getUserInformation() {
        viewModelScope.launch {
            kotlin.runCatching {
                getUserInformationUseCase(getUserIdUseCase())
            }.onSuccess {
                _user.value = it
                if (it.userAge == 0) {
                    userAge.value = null
                } else {
                    userAge.value = it.userAge.toString()
                }
                userMbti.value = it.userMbti
                userDescription.value = it.userDescription
            }.onFailure {
                Timber.e(it)
            }
        }
    }

    fun editProfile() {
        viewModelScope.launch {
            user.value?.let {
                kotlin.runCatching {
                    updateUserInformationUseCase(
                        it.userId,
                        it.userPassword,
                        it.userName,
                        userAge.value?.toInt(),
                        userMbti.value,
                        it.userImage,
                        userDescription.value
                    )
                }.onSuccess {
                    _user.value = it
                    if (it.userAge == 0) {
                        userAge.value = null
                    } else {
                        userAge.value = it.userAge.toString()
                    }
                    userMbti.value = it.userMbti
                    userDescription.value = it.userDescription
                    _updateSuccess.value = true
                }.onFailure {
                    userAge.value = user.value?.userAge.toString()
                    userMbti.value = user.value?.userMbti
                    userDescription.value = user.value?.userDescription
                    _updateSuccess.value = false
                }
            }
        }
    }
}