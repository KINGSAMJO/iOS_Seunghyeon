package co.kr.sopt_seminar_30th.domain.usecase.user

import co.kr.sopt_seminar_30th.domain.entity.user.SignUpUserInformation
import co.kr.sopt_seminar_30th.domain.repository.UserRepository
import co.kr.sopt_seminar_30th.domain.usecase.base.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class InsertUserInformationUseCase @Inject constructor(
    private val repository: UserRepository,
    coroutineDispatcher: CoroutineDispatcher
) : CoroutineUseCase<SignUpUserInformation, Long>(coroutineDispatcher) {
    override suspend fun execute(parameter: SignUpUserInformation): Long {
        return repository.insertUserInformation(parameter)
    }
}