package co.kr.sopt_seminar_30th.domain.usecase.user

import co.kr.sopt_seminar_30th.di.IoDispatcher
import co.kr.sopt_seminar_30th.domain.entity.user.UserInformation
import co.kr.sopt_seminar_30th.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserInformationUseCase @Inject constructor(
    private val repository: UserRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(parameter: String): UserInformation {
        return withContext(coroutineDispatcher) {
            repository.getUserInformation(parameter)
        }
    }
}