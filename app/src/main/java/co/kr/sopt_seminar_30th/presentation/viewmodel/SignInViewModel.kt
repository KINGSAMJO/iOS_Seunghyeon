package co.kr.sopt_seminar_30th.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.sopt_seminar_30th.domain.entity.UserInformation
import co.kr.sopt_seminar_30th.domain.usecase.user.GetUserIdUseCase
import co.kr.sopt_seminar_30th.domain.usecase.user.LoginUseCase
import co.kr.sopt_seminar_30th.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val loginUseCase: LoginUseCase
): ViewModel() {
    var userId = MutableLiveData<String>()
    var userPassword = MutableLiveData<String>()

    private var _userInformation = MutableLiveData<UserInformation>()
    val userInformation: LiveData<UserInformation> get() = _userInformation

    private var _isSuccess = SingleLiveEvent<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    fun login() {
        if(!userId.value.isNullOrBlank() && !userId.value.isNullOrBlank()) {
            viewModelScope.launch {
                kotlin.runCatching {
                    loginUseCase(userId.value!!, userPassword.value!!)
                }.onSuccess {
                    _isSuccess.value = it
                }.onFailure {
                    _isSuccess.value = false
                    Timber.e(it)
                }
            }
        }
    }

    fun getPreferenceUserId() {
        viewModelScope.launch {
            kotlin.runCatching {
                getUserIdUseCase()
            }.onSuccess {
                userId.value = it
            }.onFailure {
                Timber.e(it)
            }
        }
    }
}