package co.kr.sopt_seminar_30th.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.sopt_seminar_30th.domain.repository.remote.SignUpRepository
import co.kr.sopt_seminar_30th.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
) : ViewModel() {
    var userId = MutableLiveData<String>()
    var userPassword = MutableLiveData<String>()
    var userName = MutableLiveData<String>()

    private var _isSuccess = SingleLiveEvent<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    private var _isDuplicated = SingleLiveEvent<Boolean>()
    val isDuplicated: LiveData<Boolean> get() = _isDuplicated

    private var _isEmpty = SingleLiveEvent<Boolean>()
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    fun signUp() {
        var email = userId.value
        var password = userPassword.value
        var name = userName.value

        if (!userId.value.isNullOrBlank() && !userPassword.value.isNullOrBlank() && !userName.value.isNullOrBlank()) {
            email = email.toString()
            password = password.toString()
            name = name.toString()
            _isEmpty.value = false

            viewModelScope.launch {
                signUpRepository.signUp(name, email, password)
                    .onSuccess {
                        _isSuccess.value = true
                    }
                    .onFailure { exception ->
                        if ((exception as? HttpException)?.code() == 409) {
                            _isDuplicated.value = true
                        } else {
                            _isSuccess.value = false
                        }
                        Timber.e(exception)
                    }
            }
        } else {
            _isEmpty.value = true
        }
    }
}