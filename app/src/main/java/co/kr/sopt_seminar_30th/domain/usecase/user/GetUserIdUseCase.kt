package co.kr.sopt_seminar_30th.domain.usecase.user

import co.kr.sopt_seminar_30th.domain.repository.UserRepository
import co.kr.sopt_seminar_30th.domain.usecase.base.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val repository: UserRepository,
    coroutineDispatcher: CoroutineDispatcher
): CoroutineUseCase<Unit, String>(coroutineDispatcher) {
    override suspend fun execute(parameter: Unit): String {
        return repository.getUserId()
    }
}