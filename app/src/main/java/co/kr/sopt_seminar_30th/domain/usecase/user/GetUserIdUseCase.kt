package co.kr.sopt_seminar_30th.domain.usecase.user

import co.kr.sopt_seminar_30th.di.IoDispatcher
import co.kr.sopt_seminar_30th.domain.repository.local.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val repository: UserRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Result<String> {
        return withContext(coroutineDispatcher) {
            kotlin.runCatching {
                repository.getUserId()
            }
        }
    }
}