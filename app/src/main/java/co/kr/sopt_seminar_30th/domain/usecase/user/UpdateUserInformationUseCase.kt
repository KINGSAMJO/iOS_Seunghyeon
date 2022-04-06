package co.kr.sopt_seminar_30th.domain.usecase.user

import co.kr.sopt_seminar_30th.domain.entity.UserInformation
import co.kr.sopt_seminar_30th.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateUserInformationUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        userId: String,
        userPassword: String,
        userName: String,
        userAge: Int?,
        userMbti: String?,
        userImage: String?,
        userDescription: String?
    ): UserInformation {
        return withContext(Dispatchers.IO) {
            repository.updateUserInformation(
                UserInformation(
                    userId,
                    userPassword,
                    userName,
                    userAge,
                    userMbti,
                    userImage,
                    userDescription
                )
            )
        }
    }
}