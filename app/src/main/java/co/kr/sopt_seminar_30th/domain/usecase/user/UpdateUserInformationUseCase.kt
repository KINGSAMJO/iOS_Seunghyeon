package co.kr.sopt_seminar_30th.domain.usecase.user

import co.kr.sopt_seminar_30th.domain.entity.user.UserInformation
import co.kr.sopt_seminar_30th.domain.repository.UserRepository
import co.kr.sopt_seminar_30th.domain.usecase.base.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UpdateUserInformationUseCase @Inject constructor(
    private val repository: UserRepository,
    coroutineDispatcher: CoroutineDispatcher
) : CoroutineUseCase<UserInformation, UserInformation>(coroutineDispatcher) {
    override suspend fun execute(parameter: UserInformation): UserInformation {
        return repository.updateUserInformation(parameter)
    }
}