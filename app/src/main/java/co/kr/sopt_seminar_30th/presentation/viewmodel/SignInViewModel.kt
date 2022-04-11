package co.kr.sopt_seminar_30th.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.sopt_seminar_30th.domain.entity.user.LoginUserInformation
import co.kr.sopt_seminar_30th.domain.entity.user.UserInformation
import co.kr.sopt_seminar_30th.domain.usecase.user.GetUserIdUseCase
import co.kr.sopt_seminar_30th.domain.usecase.user.LoginUseCase
import co.kr.sopt_seminar_30th.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("NullSafeMutableLiveData")
@HiltViewModel
class SignInViewModel @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    var userId = MutableLiveData<String>()
    var userPassword = MutableLiveData<String>()

    private var _userInformation = MutableLiveData<UserInformation>()
    val userInformation: LiveData<UserInformation> get() = _userInformation

    private var _isSuccess = SingleLiveEvent<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    private var _isEmpty = SingleLiveEvent<Boolean>()
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    init {
        viewModelScope.launch {
//            val result = getUserIdUseCase(Unit)
//            when (result) {
//                is Result.Success -> userId.value = result.data
//                is Result.Error -> Timber.e(result.exception)
//            }
            kotlin.runCatching {
                getUserIdUseCase()
            }.onSuccess {
                userId.value = it
            }.onFailure {
                Timber.e(it)
            }
        }
    }

    fun login() {
        if (!userId.value.isNullOrBlank() && !userPassword.value.isNullOrBlank()) {
            _isEmpty.value = false
            viewModelScope.launch {
                kotlin.runCatching {
                    loginUseCase(LoginUserInformation(userId.value!!, userPassword.value!!))
                }.onSuccess {
                    _isSuccess.value = it
                }.onFailure {
                    _isSuccess.value = false
                    Timber.e(it)
                }
            }
        } else {
            _isEmpty.value = true
        }
    }
}