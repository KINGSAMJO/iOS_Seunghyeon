package co.kr.sopt_seminar_30th.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.sopt_seminar_30th.domain.entity.UserInformation
import co.kr.sopt_seminar_30th.domain.usecase.user.GetUserIdUseCase
import co.kr.sopt_seminar_30th.domain.usecase.user.GetUserInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val getUserIdUseCase: GetUserIdUseCase
) : ViewModel() {
    private var _user = MutableLiveData<UserInformation>()
    val user: LiveData<UserInformation> get() = _user

    fun getUserInformation() {
        viewModelScope.launch {
            kotlin.runCatching {
                getUserInformationUseCase(getUserIdUseCase())
            }.onSuccess {
                _user.value = it
            }.onFailure {
                Timber.e(it)
            }
        }
    }
}