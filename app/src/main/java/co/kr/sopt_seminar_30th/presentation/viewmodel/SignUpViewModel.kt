package co.kr.sopt_seminar_30th.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.sopt_seminar_30th.domain.entity.user.SignUpUserInformation
import co.kr.sopt_seminar_30th.domain.usecase.user.InsertUserInformationUseCase
import co.kr.sopt_seminar_30th.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val insertUserInformationUseCase: InsertUserInformationUseCase
) : ViewModel() {
    var userId = MutableLiveData<String>()
    var userPassword = MutableLiveData<String>()
    var userName = MutableLiveData<String>()

    private var _isSuccess = SingleLiveEvent<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    private var _isEmpty = SingleLiveEvent<Boolean>()
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    fun signUp() {
        if (!userId.value.isNullOrBlank() && !userPassword.value.isNullOrBlank() && !userName.value.isNullOrBlank()) {
            _isEmpty.value = false
            viewModelScope.launch {
                kotlin.runCatching {
                    insertUserInformationUseCase(
                        SignUpUserInformation(
                            userId.value!!,
                            userPassword.value!!,
                            userName.value!!
                        )
                    )
                }.onSuccess {
                    _isSuccess.value = true
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