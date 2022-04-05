package co.kr.sopt_seminar_30th.domain.repository

import co.kr.sopt_seminar_30th.domain.entity.UserInformation

interface UserRepository {
    suspend fun getUserInformation(userId: String): UserInformation
    suspend fun insertUserInformation(userInformation: UserInformation)
    suspend fun updateUserInformation(userInformation: UserInformation)
}