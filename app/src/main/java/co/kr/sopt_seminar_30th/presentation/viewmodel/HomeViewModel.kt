package co.kr.sopt_seminar_30th.presentation.viewmodel

import android.annotation.SuppressLint
import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.sopt_seminar_30th.domain.entity.user.UserInformation
import co.kr.sopt_seminar_30th.domain.usecase.base.Result
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
            val uid = getUserIdUseCase(Unit)
            userId = when (uid) {
                is Result.Success -> uid.data
                else -> ""
            }

            val userInfo = getUserInformationUseCase(userId)
            when (userInfo) {
                is Result.Success -> {
                    _user.value = userInfo.data
                    when (userInfo.data.userAge) {
                        0 -> userAge.value = null
                        else -> userAge.value = userInfo.data.userAge.toString()
                    }
                    userMbti.value = userInfo.data.userMbti
                    userImage.value = userInfo.data.userImage?.toUri()
                    userDescription.value = userInfo.data.userDescription
                }
                is Result.Error -> Timber.e(userInfo.exception)
            }
        }
    }

    fun editProfile() {
        viewModelScope.launch {
            user.value?.let {
                val result = updateUserInformationUseCase(
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

                when (result) {
                    is Result.Success -> {
                        _user.value = result.data
                        if (result.data.userAge == 0) {
                            userAge.value = null
                        } else {
                            userAge.value = result.data.userAge.toString()
                        }
                        userMbti.value = result.data.userMbti
                        userImage.value = result.data.userImage?.toUri()
                        userDescription.value = result.data.userDescription
                        _updateSuccess.value = true
                    }
                    is Result.Error -> {
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
}