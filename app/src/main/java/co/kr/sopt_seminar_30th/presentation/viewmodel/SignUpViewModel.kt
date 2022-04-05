package co.kr.sopt_seminar_30th.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.kr.sopt_seminar_30th.domain.usecase.user.InsertUserInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val insertUserInformationUseCase: InsertUserInformationUseCase
): ViewModel() {
    private var _userId = MutableLiveData<String>()
    val userId get() = _userId

    private var _userPassword = MutableLiveData<String>()
    val userPassword get() = _userPassword

    private var _userName = MutableLiveData<String>()
    val userName get() = _userName
}