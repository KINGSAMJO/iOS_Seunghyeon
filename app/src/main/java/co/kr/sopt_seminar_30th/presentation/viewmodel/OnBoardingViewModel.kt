package co.kr.sopt_seminar_30th.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.sopt_seminar_30th.domain.repository.local.AuthorizationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val authorizationRepository: AuthorizationRepository
): ViewModel() {
    private val _autoLoginState = MutableStateFlow(false)
    val autoLoginState = _autoLoginState.asStateFlow()

    fun checkAuthorization(userId: String) {
        viewModelScope.launch {
            runCatching {
                authorizationRepository.getAuthorization(userId)
            }.onSuccess {
                _autoLoginState.emit(true)
            }.onFailure {
                _autoLoginState.emit(false)
                Timber.e(it)
            }
        }
    }
}