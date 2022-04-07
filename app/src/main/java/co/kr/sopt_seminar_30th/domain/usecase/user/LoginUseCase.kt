package co.kr.sopt_seminar_30th.domain.usecase.user

import co.kr.sopt_seminar_30th.domain.entity.user.LoginUserInformation
import co.kr.sopt_seminar_30th.domain.repository.UserRepository
import co.kr.sopt_seminar_30th.domain.usecase.base.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UserRepository,
    coroutineDispatcher: CoroutineDispatcher
) : CoroutineUseCase<LoginUserInformation, Boolean>(coroutineDispatcher) {
    override suspend fun execute(parameter: LoginUserInformation): Boolean {
        return repository.login(parameter)
    }
}