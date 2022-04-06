package co.kr.sopt_seminar_30th.domain.usecase.user

import co.kr.sopt_seminar_30th.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): String {
        return withContext(Dispatchers.IO) {
            repository.getUserId()
        }
    }
}