package co.kr.sopt_seminar_30th.domain.usecase.user

import co.kr.sopt_seminar_30th.domain.entity.UserInformation
import co.kr.sopt_seminar_30th.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertUserInformationUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        userId: String,
        userPassword: String,
        userName: String
    ): Long {
        return withContext(Dispatchers.IO) {
            repository.insertUserInformation(
                UserInformation(
                    userId,
                    userPassword,
                    userName,
                    0,
                    null,
                    null,
                    null
                )
            )
        }
    }
}