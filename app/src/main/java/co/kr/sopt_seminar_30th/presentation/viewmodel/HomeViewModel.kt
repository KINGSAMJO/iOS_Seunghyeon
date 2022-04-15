package co.kr.sopt_seminar_30th.presentation.viewmodel

import android.annotation.SuppressLint
import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.sopt_seminar_30th.domain.entity.user.UserInformation
import co.kr.sopt_seminar_30th.domain.usecase.user.GetUserIdUseCase
import co.kr.sopt_seminar_30th.domain.usecase.user.GetUserInformationUseCase
import co.kr.sopt_seminar_30th.domain.usecase.user.UpdateUserInformationUseCase
import co.kr.sopt_seminar_30th.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("NullSafeMutableLiveData")
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val updateUserInformationUseCase: UpdateUserInformationUseCase
) : ViewModel() {
    private var userId: String = ""

    private var _user = MutableLiveData<UserInformation>()
    val user: LiveData<UserInformation> get() = _user

    var userAge = MutableLiveData<String?>()
    var userMbti = MutableLiveData<String?>()
    var userDescription = MutableLiveData<String?>()
    var userImage = MutableLiveData<Uri?>()

    private var _updateSuccess = SingleLiveEvent<Boolean>()
    val updateSuccess: LiveData<Boolean> get() = _updateSuccess

    fun getUserInformation() {
        viewModelScope.launch {
            kotlin.runCatching {
                getUserIdUseCase()
            }.onSuccess {
                userId = it
            }.onFailure {
                userId = ""
                error("Authorization error")
            }

            kotlin.runCatching {
                getUserInformationUseCase(userId)
            }.onSuccess {
                _user.value = it

                when (it.userAge) {
                    0 -> userAge.value = null
                    else -> userAge.value = it.userAge.toString()
                }
                userMbti.value = it.userMbti
                userImage.value = it.userImage?.toUri()
                userDescription.value = it.userDescription
            }.onFailure {
                Timber.e(it)
                error("Failed while loading user information from DB")
            }
        }
    }

    fun editProfile() {
        viewModelScope.launch {
            user.value?.let {
                kotlin.runCatching {
                    updateUserInformationUseCase(
                        UserInformation(
                            it.userId,
                            it.userPassword,
                            it.userName,
                            userAge.value?.toInt(),
                            userMbti.value,
                            userImage.value.toString(),
                            userDescription.value
                        )
                    )
                }.onSuccess {
                    _user.value = it

                    userAge.value = when (it.userAge) {
                        0 -> null
                        else -> it.userAge.toString()
                    }
                    userMbti.value = it.userMbti
                    userImage.value = it.userImage?.toUri()
                    userDescription.value = it.userDescription
                    _updateSuccess.value = true
                }.onFailure {
                    userAge.value = user.value?.userAge.toString()
                    userMbti.value = user.value?.userMbti
                    userImage.value = user.value?.userImage?.toUri()
                    userDescription.value = user.value?.userDescription
                    _updateSuccess.value = false
                }
            }
        }
    }
}