package co.kr.sopt_seminar_30th.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.sopt_seminar_30th.domain.repository.remote.SignInRepository
import co.kr.sopt_seminar_30th.domain.usecase.user.GetAutoLoginUseCase
import co.kr.sopt_seminar_30th.domain.usecase.user.GetUserIdUseCase
import co.kr.sopt_seminar_30th.domain.usecase.user.LoginUseCase
import co.kr.sopt_seminar_30th.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("NullSafeMutableLiveData")
@HiltViewModel
class SignInViewModel @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val loginUseCase: LoginUseCase,
    private val getAutoLoginUseCase: GetAutoLoginUseCase,
    private val signInRepository: SignInRepository
) : ViewModel() {
    var userId = MutableLiveData<String>()
    var userPassword = MutableLiveData<String>()

    private var _autoLogin = MutableLiveData<Boolean>(false)
    val autoLogin: LiveData<Boolean> get() = _autoLogin

    private var _isSuccess = SingleLiveEvent<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    private var _isEmpty = SingleLiveEvent<Boolean>()
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    private var _isEmailIncorrect = SingleLiveEvent<Boolean>()
    val isEmailIncorrect: LiveData<Boolean> get() = _isEmailIncorrect

    private var _isPasswordIncorrect = SingleLiveEvent<Boolean>()
    val isPasswordIncorrect: LiveData<Boolean> get() = _isPasswordIncorrect

    fun login() {
        if (!userId.value.isNullOrBlank() && !userPassword.value.isNullOrBlank()) {
            _isEmpty.value = false
            val email = userId.value.toString()
            val password = userPassword.value.toString()

            viewModelScope.launch {
                signInRepository.signIn(email, password)
                    .onSuccess {
                        _isSuccess.value = true
                        loginUseCase(email, password)
                    }
                    .onFailure { exception ->
                        when ((exception as HttpException).code()) {
                            404 -> _isEmailIncorrect.value = true
                            409 -> _isPasswordIncorrect.value = true
                            else -> _isSuccess.value = false
                        }
                        Timber.e(exception)
                    }
            }
        } else {
            _isEmpty.value = true
        }
    }
}